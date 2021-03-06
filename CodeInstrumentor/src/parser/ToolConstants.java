/* Generated By:JJTree&JavaCC: Do not edit this line. ToolConstants.java */
package parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ToolConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int OBR = 8;
  /** RegularExpression Id. */
  int CBR = 9;
  /** RegularExpression Id. */
  int COMMA = 10;
  /** RegularExpression Id. */
  int STAR = 11;
  /** RegularExpression Id. */
  int CALL = 12;
  /** RegularExpression Id. */
  int CLASS = 13;
  /** RegularExpression Id. */
  int INSTANCE = 14;
  /** RegularExpression Id. */
  int EVENT = 15;
  /** RegularExpression Id. */
  int STRCODE = 16;
  /** RegularExpression Id. */
  int DIGIT = 17;
  /** RegularExpression Id. */
  int INTEGER = 18;
  /** RegularExpression Id. */
  int LETTER = 19;
  /** RegularExpression Id. */
  int STRING = 20;
  /** RegularExpression Id. */
  int ENDCODE = 21;
  /** RegularExpression Id. */
  int CODE = 22;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int CODEREADING = 1;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\n\"",
    "\"\\r\"",
    "\"r\\n\"",
    "\"\\t\"",
    "<token of kind 6>",
    "<token of kind 7>",
    "\"(\"",
    "\")\"",
    "\",\"",
    "\"*\"",
    "<CALL>",
    "\"@Class:\"",
    "\"@Instance:\"",
    "\"@Event:\"",
    "\"@Code:\"",
    "<DIGIT>",
    "<INTEGER>",
    "<LETTER>",
    "<STRING>",
    "\"@EndCode\"",
    "<CODE>",
  };

}
