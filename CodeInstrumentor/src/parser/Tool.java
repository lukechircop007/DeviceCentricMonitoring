/* Generated By:JJTree&JavaCC: Do not edit this line. Tool.java */
package parser;
import java.io.FileInputStream;
import java.util.HashMap;

import Instrumentor.InstrumentorMain;
import states.*;

public class Tool/*@bgen(jjtree)*/implements ToolTreeConstants, ToolConstants {/*@bgen(jjtree)*/
  protected JJTToolState jjtree = new JJTToolState();
        public static void main( String[] args ){
                try{
                    InstrumentorMain inst = new InstrumentorMain();
                	HashMap<String,String> settingsList;
            		try {
            			settingsList = inst.loadSettings();		
            			System.setIn(new FileInputStream(settingsList.get("instrumentationCodeLocation")+"/RuntimeMonitor/ApplicationSide/EventInstrumentations.txt"));
            		} catch (Exception e) {
            			System.out.println("Error while trying to load Settings... "+e.getMessage());
            		}                	
                    Tool parser = new Tool( System.in );
                    ClassRepresentation n = parser.Program();
                    inst.startInstrumentation(n);
                }catch(Exception e){
                        System.out.println("Error: "+e);
                }
        }

//****************************************FIN************************************************************
  final public String Class() throws ParseException {
                /*@bgen(jjtree) Class */
        SimpleNode jjtn000 = new SimpleNode(JJTCLASS);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(CLASS);
      t = jj_consume_token(STRING);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return t.toString();}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public String Instance() throws ParseException {
                   /*@bgen(jjtree) Instance */
        SimpleNode jjtn000 = new SimpleNode(JJTINSTANCE);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(INSTANCE);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
        t = jj_consume_token(STAR);
        break;
      case STRING:
        t = jj_consume_token(STRING);
        break;
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return t.toString();}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public String Type() throws ParseException {
               /*@bgen(jjtree) Type */
        SimpleNode jjtn000 = new SimpleNode(JJTTYPE);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return t.toString();}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public Parameters Parameters() throws ParseException {
                         /*@bgen(jjtree) Parameters */
        SimpleNode jjtn000 = new SimpleNode(JJTPARAMETERS);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);String tt;
        Token t;
        Parameters parameter;
    try {
         parameter = new Parameters();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STAR:
      case STRING:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          tt = Type();
                       parameter.addParameterType(tt);
          t = jj_consume_token(STRING);
                        parameter.addParameterName(t.toString());
          break;
        case STAR:
          t = jj_consume_token(STAR);
                      parameter.setStar();
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        label_1:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case COMMA:
            ;
            break;
          default:
            jj_la1[2] = jj_gen;
            break label_1;
          }
          jj_consume_token(COMMA);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case STRING:
            tt = Type();
                        parameter.addParameterType(tt);
            t = jj_consume_token(STRING);
                         parameter.addParameterName(t.toString());
            break;
          case STAR:
            t = jj_consume_token(STAR);
                      parameter.setStar();
            break;
          default:
            jj_la1[3] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return parameter;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void Event(InstrumentationEvent instEvent) throws ParseException {
                                            /*@bgen(jjtree) Event */
        SimpleNode jjtn000 = new SimpleNode(JJTEVENT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token t;
        Parameters param;
    try {
      jj_consume_token(EVENT);
      t = jj_consume_token(STRING);
                     instEvent.setEventName(t.toString());
      jj_consume_token(OBR);
      param = Parameters();
                                     instEvent.setEventParameters(param);
      jj_consume_token(CBR);
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public String Code() throws ParseException {
               /*@bgen(jjtree) Code */
        SimpleNode jjtn000 = new SimpleNode(JJTCODE);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token t;
        String code = "";
    try {
      jj_consume_token(STRCODE);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CODE:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_2;
        }
        t = jj_consume_token(CODE);
                            code += t.toString();
      }
      jj_consume_token(ENDCODE);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return code;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void Start(ClassRepresentation clsRep) throws ParseException {
                                        /*@bgen(jjtree) Start */
        SimpleNode jjtn000 = new SimpleNode(JJTSTART);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);Token t;
        String className;
        String temp;
        InstrumentationEvent instEvent;
    try {
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CALL:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_3;
        }
                 instEvent = new InstrumentationEvent();
        t = jj_consume_token(CALL);
                            instEvent.setPointOfInstrumentation(t.toString());
        className = Class();
        temp = Instance();
                                  instEvent.setEventInstance(temp);
        Event(instEvent);
        temp = Code();
                              instEvent.setCodeToBeInstrumented(temp);
                 clsRep.addInstrumentation(className,instEvent);
      }
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public ClassRepresentation Program() throws ParseException {
                               /*@bgen(jjtree) Program */
        SimpleNode jjtn000 = new SimpleNode(JJTPROGRAM);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);ClassRepresentation clsRep;
    try {
         clsRep = new ClassRepresentation();
      Start(clsRep);
      jj_consume_token(0);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return clsRep;}
    } catch (Throwable jjte000) {
          if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ToolTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[7];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x100800,0x100800,0x400,0x100800,0x100800,0x400000,0x1000,};
   }

  /** Constructor with InputStream. */
  public Tool(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Tool(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ToolTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Tool(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ToolTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Tool(ToolTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ToolTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 7; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}