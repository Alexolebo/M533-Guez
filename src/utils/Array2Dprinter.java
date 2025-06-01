package utils;

public class Array2Dprinter {
   private static int SPACING = 1;
   private static Style highlightTextStyle;
   private static Color highlightTextColor;
   private static Color highlightBackgroundColor;
   private static Color grayedOutTextColor;
   private static Style grayedOutTextStyle;
   private static Color grayedOutBackgroundColor;

   public Array2Dprinter() {
   }

   public static void setHighlightStyle(Style style, Color color, Color backgroundColor) {
      highlightTextColor = color;
      highlightTextStyle = style;
      highlightBackgroundColor = backgroundColor;
   }

   public static void setGrayedOutStyle(Style style, Color color, Color backgroundColor) {
      grayedOutTextColor = color;
      grayedOutTextStyle = style;
      grayedOutBackgroundColor = backgroundColor;
   }

   public static String print2DArray(IPrintable[][] array2D, int highlightRow, int highlightColumn) {
      StringBuilder output = new StringBuilder();
      int maxLength = findMaxLength(array2D);
      int numColumns = array2D[0].length;

      for(int row = 0; row < array2D.length; ++row) {
         appendHorizontalLine(output, maxLength, numColumns);
         appendElements(output, array2D[row], maxLength, row == highlightRow, highlightColumn);
      }

      appendHorizontalLine(output, maxLength, numColumns);
      return output.toString();
   }

   private static <T> void appendHorizontalLine(StringBuilder output, int maxLength, int numColumns) {
      for(int col = 0; col < numColumns; ++col) {
         output.append("+");
         output.append("-".repeat(maxLength + 2 * SPACING));
      }

      output.append("+");
      output.append(System.lineSeparator());
   }

   private static void appendElements(StringBuilder output, IPrintable[] row, int maxLength, boolean isHighlightRow, int highlightColumn) {
      output.append("|");

      for(int col = 0; col < row.length; ++col) {
         IPrintable element = row[col];
         String text = element != null ? element.getPrintableString() : "";
         double padding = (double)(maxLength - text.length()) * 0.5;
         int paddingBefore = (int)Math.floor(padding);
         int paddingAfter = (int)Math.ceil(padding);
         String paddingSpacesBefore = " ".repeat(paddingBefore);
         String paddingSpacesAfter = " ".repeat(paddingAfter);
         text = paddingSpacesBefore + text + paddingSpacesAfter;
         if (isHighlightRow && col == highlightColumn) {
            text = StringStyling.StyleString(text, highlightTextStyle, highlightTextColor, highlightBackgroundColor);
         } else if (element != null && element.isGrayedOut()) {
            text = StringStyling.StyleString(text, grayedOutTextStyle, grayedOutTextColor, grayedOutBackgroundColor);
         }

         output.append(" ".repeat(SPACING)).append(text).append(" ".repeat(SPACING)).append("|");
      }

      output.append(System.lineSeparator());
   }

   private static int findMaxLength(IPrintable[][] array) {
      int maxLength = 0;
      IPrintable[][] var2 = array;
      int var3 = array.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         IPrintable[] row = var2[var4];
         IPrintable[] var6 = row;
         int var7 = row.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            IPrintable element = var6[var8];
            if (element != null && element.toString().length() > maxLength) {
               maxLength = element.toString().length();
            }
         }
      }

      return maxLength;
   }

   static {
      highlightTextStyle = Style.BOLD;
      highlightTextColor = Color.WHITE;
      highlightBackgroundColor = Color.BLUE;
      grayedOutTextColor = Color.BLACK;
      grayedOutTextStyle = Style.NORMAL;
      grayedOutBackgroundColor = Color.BLACK;
   }
}
