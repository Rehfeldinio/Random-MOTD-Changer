package de.rehfeldinio.motd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradientUtil {

    // Matches: <gradient:#RRGGBB:#RRGGBB>text</gradient>
    // Also supports optional third color: <gradient:#RRGGBB:#RRGGBB:#RRGGBB>text</gradient>
    private static final Pattern GRADIENT_PATTERN =
            Pattern.compile("<gradient:(#[A-Fa-f0-9]{6})(:#[A-Fa-f0-9]{6})(:#[A-Fa-f0-9]{6})?>(.*?)</gradient>");

    // Matches: <hex:#RRGGBB>text</hex> for single hex colors
    private static final Pattern HEX_PATTERN =
            Pattern.compile("<hex:(#[A-Fa-f0-9]{6})>(.*?)</hex>");

    /**
     * Verarbeitet einen String mit Gradient- und Hex-Tags und gibt ein Adventure Component zurueck.
     * Standard &-Farbcodes werden ebenfalls unterstuetzt.
     */
    public static Component process(String message) {
        // Zeilenumbrueche verarbeiten
        message = message.replace("\\\\n", "\n").replace("\\n", "\n").replace("&nl", "\n");

        // Gradient-Tags verarbeiten
        message = applyGradients(message);

        // Hex-Farb-Tags verarbeiten
        message = applyHexColors(message);

        // & Farbcodes in Section-Sign umwandeln
        message = message.replace('&', '\u00A7');

        // Adventure Component ueber den Legacy-Serializer erzeugen (unterstuetzt Hex-Farben)
        return LegacyComponentSerializer.legacySection().deserialize(message);
    }

    private static String applyGradients(String message) {
        Matcher matcher = GRADIENT_PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            Color startColor = Color.decode(matcher.group(1));
            Color endColor = Color.decode(matcher.group(2).substring(1)); // Remove leading ':'
            Color midColor = null;
            if (matcher.group(3) != null) {
                midColor = Color.decode(matcher.group(3).substring(1)); // Remove leading ':'
            }
            String text = matcher.group(4);

            String gradientText;
            if (midColor != null) {
                gradientText = applyThreeColorGradient(text, startColor, midColor, endColor);
            } else {
                gradientText = applyTwoColorGradient(text, startColor, endColor);
            }
            matcher.appendReplacement(sb, Matcher.quoteReplacement(gradientText));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String applyHexColors(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1);
            String text = matcher.group(2);
            String colored = colorize(hex) + text;
            matcher.appendReplacement(sb, Matcher.quoteReplacement(colored));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String applyTwoColorGradient(String text, Color start, Color end) {
        StringBuilder result = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            float ratio = (length == 1) ? 0 : (float) i / (length - 1);
            Color color = interpolate(start, end, ratio);
            result.append(colorize(toHex(color)));
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    private static String applyThreeColorGradient(String text, Color start, Color mid, Color end) {
        StringBuilder result = new StringBuilder();
        int length = text.length();
        int half = length / 2;

        for (int i = 0; i < length; i++) {
            Color color;
            if (i <= half) {
                float ratio = (half == 0) ? 0 : (float) i / half;
                color = interpolate(start, mid, ratio);
            } else {
                float ratio = (length - 1 - half == 0) ? 1 : (float) (i - half) / (length - 1 - half);
                color = interpolate(mid, end, ratio);
            }
            result.append(colorize(toHex(color)));
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    private static Color interpolate(Color from, Color to, float ratio) {
        int r = Math.round(from.getRed() + ratio * (to.getRed() - from.getRed()));
        int g = Math.round(from.getGreen() + ratio * (to.getGreen() - from.getGreen()));
        int b = Math.round(from.getBlue() + ratio * (to.getBlue() - from.getBlue()));
        return new Color(
                Math.max(0, Math.min(255, r)),
                Math.max(0, Math.min(255, g)),
                Math.max(0, Math.min(255, b))
        );
    }

    /**
     * Converts a hex color string like "#FF5555" into the Minecraft protocol
     * color format: section sign followed by each hex character prefixed with section sign.
     * Example: #FF5555 -> \u00A7x\u00A7F\u00A7F\u00A75\u00A75\u00A75\u00A75
     */
    private static String colorize(String hex) {
        hex = hex.replace("#", "");
        StringBuilder mc = new StringBuilder("\u00A7x");
        for (char c : hex.toCharArray()) {
            mc.append("\u00A7").append(c);
        }
        return mc.toString();
    }

    private static String toHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
