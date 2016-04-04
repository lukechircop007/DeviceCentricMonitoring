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
  int OPAR = 8;
  /** RegularExpression Id. */
  int CPAR = 9;
  /** RegularExpression Id. */
  int OBR = 10;
  /** RegularExpression Id. */
  int CBR = 11;
  /** RegularExpression Id. */
  int AT = 12;
  /** RegularExpression Id. */
  int OSBR = 13;
  /** RegularExpression Id. */
  int CSBR = 14;
  /** RegularExpression Id. */
  int COL = 15;
  /** RegularExpression Id. */
  int SCOL = 16;
  /** RegularExpression Id. */
  int COMMA = 17;
  /** RegularExpression Id. */
  int FSTP = 18;
  /** RegularExpression Id. */
  int EQ = 19;
  /** RegularExpression Id. */
  int BNDS = 20;
  /** RegularExpression Id. */
  int BNDE = 21;
  /** RegularExpression Id. */
  int BNDEBNDE = 22;
  /** RegularExpression Id. */
  int SLSH = 23;
  /** RegularExpression Id. */
  int FSLSH = 24;
  /** RegularExpression Id. */
  int ANDAND = 25;
  /** RegularExpression Id. */
  int AND = 26;
  /** RegularExpression Id. */
  int OROR = 27;
  /** RegularExpression Id. */
  int OR = 28;
  /** RegularExpression Id. */
  int NULL = 29;
  /** RegularExpression Id. */
  int THIS = 30;
  /** RegularExpression Id. */
  int SUPER = 31;
  /** RegularExpression Id. */
  int RULES = 32;
  /** RegularExpression Id. */
  int IMPORT = 33;
  /** RegularExpression Id. */
  int GLOBAL = 34;
  /** RegularExpression Id. */
  int TIMERS = 35;
  /** RegularExpression Id. */
  int STATES = 36;
  /** RegularExpression Id. */
  int EVENTS = 37;
  /** RegularExpression Id. */
  int STATESAVE = 38;
  /** RegularExpression Id. */
  int STATELOAD = 39;
  /** RegularExpression Id. */
  int ACTIONS = 40;
  /** RegularExpression Id. */
  int IMPORTS = 41;
  /** RegularExpression Id. */
  int INCLUDE = 42;
  /** RegularExpression Id. */
  int STATEDEC = 43;
  /** RegularExpression Id. */
  int CONDITIONS = 44;
  /** RegularExpression Id. */
  int INSTANCEOF = 45;
  /** RegularExpression Id. */
  int KERNEL = 46;
  /** RegularExpression Id. */
  int TIMEROFF = 47;
  /** RegularExpression Id. */
  int TIMEUNDER = 48;
  /** RegularExpression Id. */
  int TIMERPAUSE = 49;
  /** RegularExpression Id. */
  int TIMERESUME = 50;
  /** RegularExpression Id. */
  int TIMERESET = 51;
  /** RegularExpression Id. */
  int APP = 52;
  /** RegularExpression Id. */
  int WHERE = 53;
  /** RegularExpression Id. */
  int UPONRETURNING = 54;
  /** RegularExpression Id. */
  int STR = 55;
  /** RegularExpression Id. */
  int STRUCT = 56;
  /** RegularExpression Id. */
  int EVENTCAPTURE = 57;
  /** RegularExpression Id. */
  int DIGIT = 58;
  /** RegularExpression Id. */
  int INTEGER = 59;
  /** RegularExpression Id. */
  int LETTER = 60;
  /** RegularExpression Id. */
  int STRING = 61;
  /** RegularExpression Id. */
  int BOBR = 62;
  /** RegularExpression Id. */
  int BCBR = 63;
  /** RegularExpression Id. */
  int BLK = 64;
  /** RegularExpression Id. */
  int HEADER = 65;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IMPORTSS = 1;
  /** Lexical state. */
  int BLOCK = 2;

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
    "\"{\"",
    "\"}\"",
    "\"@\"",
    "\"[\"",
    "\"]\"",
    "\":\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\"=\"",
    "\"<\"",
    "\">\"",
    "\">>\"",
    "\"/\"",
    "\"\\\\\"",
    "\"&&\"",
    "\"&\"",
    "\"||\"",
    "\"|\"",
    "\"null\"",
    "\"this\"",
    "\"super\"",
    "\"rules\"",
    "\"import\"",
    "\"global\"",
    "\"timers\"",
    "\"states\"",
    "\"events\"",
    "\"saveWith\"",
    "\"loadWith\"",
    "\"actions\"",
    "\"imports\"",
    "\"#include\"",
    "\"declaration\"",
    "\"conditions\"",
    "\"instanceof\"",
    "\"kernelSide\"",
    "\"RV:timerOff\"",
    "\"RV:timeUnder\"",
    "\"RV:timerPause\"",
    "\"RV:timerResume\"",
    "\"RV:timerReset\"",
    "\"applicationSide\"",
    "\"where\"",
    "\"uponReturning\"",
    "\"*\"",
    "\"struct\"",
    "<EVENTCAPTURE>",
    "<DIGIT>",
    "<INTEGER>",
    "<LETTER>",
    "<STRING>",
    "\"{\"",
    "\"}\"",
    "<BLK>",
    "\".h\"",
    "\"@%\"",
    "\"->\"",
  };

}