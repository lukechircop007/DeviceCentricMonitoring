/* Generated By:JJTree&JavaCC: Do not edit this line. ToolTokenManager.java */
package parser;
import java.io.FileInputStream;
import Instrumentor.InstrumentorMain;
import states.*;

/** Token Manager. */
public class ToolTokenManager implements ToolConstants
{
        void CommonTokenAction(Token t){
                //System.out.println("Token: "+t.toString());
        }

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1e000L) != 0L)
            return 10;
         if ((active0 & 0x10L) != 0L)
         {
            jjmatchedKind = 20;
            return 28;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 8);
      case 41:
         return jjStopAtPos(0, 9);
      case 42:
         return jjStopAtPos(0, 11);
      case 44:
         return jjStopAtPos(0, 10);
      case 64:
         return jjMoveStringLiteralDfa1_0(0x1e000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x10L);
      default :
         return jjMoveNfa_0(1, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 10:
         if ((active0 & 0x10L) != 0L)
            return jjStopAtPos(1, 4);
         break;
      case 67:
         return jjMoveStringLiteralDfa2_0(active0, 0x12000L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 118:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 100:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000L);
      case 115:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(5, 16);
         break;
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000L);
      case 115:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(6, 13);
         else if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(6, 15);
         break;
      case 110:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 58:
         if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(9, 14);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 28;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 28:
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjCheckNAddTwoStates(2, 3);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 18)
                        kind = 18;
                     jjCheckNAdd(0);
                  }
                  else if (curChar == 47)
                     jjAddStates(0, 1);
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjCheckNAdd(0);
                  break;
               case 16:
                  if (curChar == 47)
                     jjAddStates(0, 1);
                  break;
               case 17:
                  if (curChar == 47)
                     jjCheckNAddStates(2, 4);
                  break;
               case 18:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(2, 4);
                  break;
               case 19:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 20:
                  if (curChar == 10 && kind > 6)
                     kind = 6;
                  break;
               case 21:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 23:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 24:
                  if (curChar == 42)
                     jjAddStates(5, 6);
                  break;
               case 25:
                  if ((0xffff7fffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(26, 24);
                  break;
               case 26:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(26, 24);
                  break;
               case 27:
                  if (curChar == 47 && kind > 7)
                     kind = 7;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 10:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 14;
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 28:
               case 3:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjCheckNAddTwoStates(2, 3);
                  break;
               case 1:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                     jjCheckNAddTwoStates(2, 3);
                  }
                  else if (curChar == 64)
                     jjAddStates(7, 8);
                  break;
               case 4:
                  if (curChar == 64)
                     jjAddStates(7, 8);
                  break;
               case 5:
                  if (curChar == 101 && kind > 12)
                     kind = 12;
                  break;
               case 6:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 11:
                  if (curChar == 114 && kind > 12)
                     kind = 12;
                  break;
               case 12:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 13:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 18:
                  jjAddStates(2, 4);
                  break;
               case 23:
                  jjCheckNAddTwoStates(23, 24);
                  break;
               case 25:
               case 26:
                  jjCheckNAddTwoStates(26, 24);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 18:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(2, 4);
                  break;
               case 23:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(23, 24);
                  break;
               case 25:
               case 26:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(26, 24);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 28 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 64:
         return jjMoveStringLiteralDfa1_1(0x200000L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa2_1(active0, 0x200000L);
      default :
         return 2;
   }
}
private int jjMoveStringLiteralDfa2_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 2;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 2;
   }
   switch(curChar)
   {
      case 110:
         return jjMoveStringLiteralDfa3_1(active0, 0x200000L);
      default :
         return 3;
   }
}
private int jjMoveStringLiteralDfa3_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 3;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 3;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa4_1(active0, 0x200000L);
      default :
         return 4;
   }
}
private int jjMoveStringLiteralDfa4_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 4;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 4;
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa5_1(active0, 0x200000L);
      default :
         return 5;
   }
}
private int jjMoveStringLiteralDfa5_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 5;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 5;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa6_1(active0, 0x200000L);
      default :
         return 6;
   }
}
private int jjMoveStringLiteralDfa6_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 6;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 6;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa7_1(active0, 0x200000L);
      default :
         return 7;
   }
}
private int jjMoveStringLiteralDfa7_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 7;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 7;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(7, 21);
         break;
      default :
         return 8;
   }
   return 8;
}
static final int[] jjnextStates = {
   17, 22, 18, 19, 21, 25, 27, 10, 15, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, "\50", "\51", "\54", "\52", null, 
"\100\103\154\141\163\163\72", "\100\111\156\163\164\141\156\143\145\72", "\100\105\166\145\156\164\72", 
"\100\103\157\144\145\72", null, null, null, null, "\100\105\156\144\103\157\144\145", null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "CODEREADING",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 0, -1, 
};
static final long[] jjtoToken = {
   0x75ff01L, 
};
static final long[] jjtoSkip = {
   0xfeL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[28];
private final int[] jjstateSet = new int[56];
protected char curChar;
/** Constructor. */
public ToolTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ToolTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 28; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 2 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      CommonTokenAction(matchedToken);
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 22)
       {
          jjmatchedKind = 22;
       }
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           CommonTokenAction(matchedToken);
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
