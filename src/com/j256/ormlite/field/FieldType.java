package com.j256.ormlite.field;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.LazyForeignCollection;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.stmt.mapped.MappedQueryForId;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class FieldType
{
  private static boolean DEFAULT_VALUE_BOOLEAN = false;
  private static byte DEFAULT_VALUE_BYTE = 0;
  private static char DEFAULT_VALUE_CHAR = '\000';
  private static double DEFAULT_VALUE_DOUBLE = 0.0D;
  private static float DEFAULT_VALUE_FLOAT = 0.0F;
  private static int DEFAULT_VALUE_INT = 0;
  private static long DEFAULT_VALUE_LONG = 0L;
  private static short DEFAULT_VALUE_SHORT = 0;
  public static final String FOREIGN_ID_FIELD_SUFFIX = "_id";
  private static final ThreadLocal<LevelCounters> threadLevelCounters = new ThreadLocal();
  private final ConnectionSource connectionSource;
  private DataPersister dataPersister;
  private Object dataTypeConfigObj;
  private final String dbColumnName;
  private Object defaultValue;
  private final Field field;
  private final DatabaseFieldConfig fieldConfig;
  private FieldConverter fieldConverter;
  private final Method fieldGetMethod;
  private final Method fieldSetMethod;
  private Constructor<?> foreignConstructor;
  private Dao<?, ?> foreignDao;
  private FieldType foreignFieldType;
  private FieldType foreignIdField;
  private final String generatedIdSequence;
  private final boolean isGeneratedId;
  private final boolean isId;
  private MappedQueryForId<Object, Object> mappedQueryForId;

  // ERROR //
  public FieldType(ConnectionSource paramConnectionSource, String paramString, Field paramField, DatabaseFieldConfig paramDatabaseFieldConfig, Class<?> paramClass)
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 87	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: aload_1
    //   6: putfield 89	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   9: aload_1
    //   10: invokeinterface 95 1 0
    //   15: astore 6
    //   17: aload_0
    //   18: aload_3
    //   19: putfield 97	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   22: aload_3
    //   23: invokevirtual 103	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   26: astore 7
    //   28: aload 4
    //   30: invokevirtual 109	com/j256/ormlite/field/DatabaseFieldConfig:getDataPersister	()Lcom/j256/ormlite/field/DataPersister;
    //   33: ifnonnull +295 -> 328
    //   36: aload 4
    //   38: invokevirtual 112	com/j256/ormlite/field/DatabaseFieldConfig:getPersisterClass	()Ljava/lang/Class;
    //   41: astore 12
    //   43: aload 12
    //   45: ifnull +10 -> 55
    //   48: aload 12
    //   50: ldc 114
    //   52: if_acmpne +88 -> 140
    //   55: aload_3
    //   56: invokestatic 120	com/j256/ormlite/field/DataPersisterManager:lookupForField	(Ljava/lang/reflect/Field;)Lcom/j256/ormlite/field/DataPersister;
    //   59: astore 8
    //   61: aload_3
    //   62: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   65: astore 9
    //   67: aload 4
    //   69: invokevirtual 128	com/j256/ormlite/field/DatabaseFieldConfig:isForeign	()Z
    //   72: ifne +11 -> 83
    //   75: aload 4
    //   77: invokevirtual 131	com/j256/ormlite/field/DatabaseFieldConfig:isForeignAutoRefresh	()Z
    //   80: ifeq +409 -> 489
    //   83: aload 8
    //   85: ifnull +308 -> 393
    //   88: aload 8
    //   90: invokeinterface 136 1 0
    //   95: ifeq +298 -> 393
    //   98: new 138	java/lang/IllegalArgumentException
    //   101: dup
    //   102: new 140	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   109: ldc 143
    //   111: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload_0
    //   115: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   118: ldc 152
    //   120: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: aload 7
    //   125: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   128: ldc 154
    //   130: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokespecial 160	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   139: athrow
    //   140: aload 12
    //   142: ldc 162
    //   144: iconst_0
    //   145: anewarray 164	java/lang/Class
    //   148: invokevirtual 168	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   151: astore 14
    //   153: aload 14
    //   155: aconst_null
    //   156: iconst_0
    //   157: anewarray 4	java/lang/Object
    //   160: invokevirtual 174	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   163: checkcast 133	com/j256/ormlite/field/DataPersister
    //   166: astore 17
    //   168: aload 17
    //   170: ifnonnull +118 -> 288
    //   173: new 82	java/sql/SQLException
    //   176: dup
    //   177: new 140	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   184: ldc 176
    //   186: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload 12
    //   191: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   200: athrow
    //   201: astore 13
    //   203: new 140	java/lang/StringBuilder
    //   206: dup
    //   207: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   210: ldc 179
    //   212: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: aload 12
    //   217: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   220: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: aload 13
    //   225: invokestatic 185	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   228: athrow
    //   229: astore 16
    //   231: new 140	java/lang/StringBuilder
    //   234: dup
    //   235: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   238: ldc 187
    //   240: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: aload 12
    //   245: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   248: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   251: aload 16
    //   253: invokevirtual 191	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   256: invokestatic 185	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   259: athrow
    //   260: astore 15
    //   262: new 140	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   269: ldc 187
    //   271: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: aload 12
    //   276: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   279: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: aload 15
    //   284: invokestatic 185	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   287: athrow
    //   288: aload 17
    //   290: checkcast 133	com/j256/ormlite/field/DataPersister
    //   293: astore 8
    //   295: goto -234 -> 61
    //   298: astore 18
    //   300: new 82	java/sql/SQLException
    //   303: dup
    //   304: new 140	java/lang/StringBuilder
    //   307: dup
    //   308: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   311: ldc 193
    //   313: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: aload 12
    //   318: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   321: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   327: athrow
    //   328: aload 4
    //   330: invokevirtual 109	com/j256/ormlite/field/DatabaseFieldConfig:getDataPersister	()Lcom/j256/ormlite/field/DataPersister;
    //   333: astore 8
    //   335: aload 8
    //   337: aload_3
    //   338: invokeinterface 197 2 0
    //   343: ifne -282 -> 61
    //   346: new 138	java/lang/IllegalArgumentException
    //   349: dup
    //   350: new 140	java/lang/StringBuilder
    //   353: dup
    //   354: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   357: ldc 199
    //   359: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: aload 7
    //   364: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   367: ldc 201
    //   369: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: aload_0
    //   373: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   376: ldc 203
    //   378: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: aload 8
    //   383: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   386: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   389: invokespecial 160	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   392: athrow
    //   393: new 140	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   400: aload 9
    //   402: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: ldc 35
    //   407: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   413: astore 9
    //   415: aload 4
    //   417: invokevirtual 206	com/j256/ormlite/field/DatabaseFieldConfig:getColumnName	()Ljava/lang/String;
    //   420: ifnonnull +408 -> 828
    //   423: aload_0
    //   424: aload 9
    //   426: putfield 208	com/j256/ormlite/field/FieldType:dbColumnName	Ljava/lang/String;
    //   429: aload_0
    //   430: aload 4
    //   432: putfield 210	com/j256/ormlite/field/FieldType:fieldConfig	Lcom/j256/ormlite/field/DatabaseFieldConfig;
    //   435: aload 4
    //   437: invokevirtual 212	com/j256/ormlite/field/DatabaseFieldConfig:isId	()Z
    //   440: ifeq +475 -> 915
    //   443: aload 4
    //   445: invokevirtual 214	com/j256/ormlite/field/DatabaseFieldConfig:isGeneratedId	()Z
    //   448: ifne +11 -> 459
    //   451: aload 4
    //   453: invokevirtual 217	com/j256/ormlite/field/DatabaseFieldConfig:getGeneratedIdSequence	()Ljava/lang/String;
    //   456: ifnull +384 -> 840
    //   459: new 138	java/lang/IllegalArgumentException
    //   462: dup
    //   463: new 140	java/lang/StringBuilder
    //   466: dup
    //   467: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   470: ldc 219
    //   472: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: aload_3
    //   476: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   479: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: invokespecial 160	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   488: athrow
    //   489: aload 4
    //   491: invokevirtual 222	com/j256/ormlite/field/DatabaseFieldConfig:isForeignCollection	()Z
    //   494: ifeq +166 -> 660
    //   497: aload 7
    //   499: ldc 224
    //   501: if_acmpeq +61 -> 562
    //   504: ldc 226
    //   506: aload 7
    //   508: invokevirtual 230	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   511: ifne +51 -> 562
    //   514: new 82	java/sql/SQLException
    //   517: dup
    //   518: new 140	java/lang/StringBuilder
    //   521: dup
    //   522: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   525: ldc 232
    //   527: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   530: aload_3
    //   531: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   534: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: ldc 234
    //   539: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: ldc 226
    //   544: invokevirtual 237	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   547: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: ldc 239
    //   552: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   558: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   561: athrow
    //   562: aload_3
    //   563: invokevirtual 243	java/lang/reflect/Field:getGenericType	()Ljava/lang/reflect/Type;
    //   566: astore 11
    //   568: aload 11
    //   570: instanceof 245
    //   573: ifne +38 -> 611
    //   576: new 82	java/sql/SQLException
    //   579: dup
    //   580: new 140	java/lang/StringBuilder
    //   583: dup
    //   584: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   587: ldc 232
    //   589: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   592: aload_3
    //   593: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   596: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: ldc 247
    //   601: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   604: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   607: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   610: athrow
    //   611: aload 11
    //   613: checkcast 245	java/lang/reflect/ParameterizedType
    //   616: invokeinterface 251 1 0
    //   621: arraylength
    //   622: ifne -207 -> 415
    //   625: new 82	java/sql/SQLException
    //   628: dup
    //   629: new 140	java/lang/StringBuilder
    //   632: dup
    //   633: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   636: ldc 232
    //   638: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   641: aload_3
    //   642: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   645: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   648: ldc 253
    //   650: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   656: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   659: athrow
    //   660: aload 8
    //   662: ifnonnull -247 -> 415
    //   665: aload 4
    //   667: invokevirtual 222	com/j256/ormlite/field/DatabaseFieldConfig:isForeignCollection	()Z
    //   670: ifne -255 -> 415
    //   673: ldc 255
    //   675: aload 7
    //   677: invokevirtual 230	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   680: ifeq +51 -> 731
    //   683: new 82	java/sql/SQLException
    //   686: dup
    //   687: new 140	java/lang/StringBuilder
    //   690: dup
    //   691: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   694: ldc_w 257
    //   697: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: aload 7
    //   702: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   705: ldc_w 259
    //   708: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: aload_3
    //   712: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   715: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   718: ldc_w 261
    //   721: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   724: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   727: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   730: athrow
    //   731: ldc_w 263
    //   734: aload 7
    //   736: invokevirtual 230	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   739: ifeq +51 -> 790
    //   742: new 82	java/sql/SQLException
    //   745: dup
    //   746: new 140	java/lang/StringBuilder
    //   749: dup
    //   750: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   753: ldc_w 257
    //   756: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: aload 7
    //   761: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   764: ldc_w 259
    //   767: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: aload_3
    //   771: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   774: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   777: ldc_w 265
    //   780: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   786: invokespecial 177	java/sql/SQLException:<init>	(Ljava/lang/String;)V
    //   789: athrow
    //   790: new 138	java/lang/IllegalArgumentException
    //   793: dup
    //   794: new 140	java/lang/StringBuilder
    //   797: dup
    //   798: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   801: ldc_w 267
    //   804: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: aload 7
    //   809: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   812: ldc 201
    //   814: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: aload_0
    //   818: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   821: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   824: invokespecial 160	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   827: athrow
    //   828: aload_0
    //   829: aload 4
    //   831: invokevirtual 206	com/j256/ormlite/field/DatabaseFieldConfig:getColumnName	()Ljava/lang/String;
    //   834: putfield 208	com/j256/ormlite/field/FieldType:dbColumnName	Ljava/lang/String;
    //   837: goto -408 -> 429
    //   840: aload_0
    //   841: iconst_1
    //   842: putfield 269	com/j256/ormlite/field/FieldType:isId	Z
    //   845: aload_0
    //   846: iconst_0
    //   847: putfield 271	com/j256/ormlite/field/FieldType:isGeneratedId	Z
    //   850: aload_0
    //   851: aconst_null
    //   852: putfield 273	com/j256/ormlite/field/FieldType:generatedIdSequence	Ljava/lang/String;
    //   855: aload_0
    //   856: getfield 269	com/j256/ormlite/field/FieldType:isId	Z
    //   859: ifeq +215 -> 1074
    //   862: aload 4
    //   864: invokevirtual 128	com/j256/ormlite/field/DatabaseFieldConfig:isForeign	()Z
    //   867: ifne +11 -> 878
    //   870: aload 4
    //   872: invokevirtual 131	com/j256/ormlite/field/DatabaseFieldConfig:isForeignAutoRefresh	()Z
    //   875: ifeq +199 -> 1074
    //   878: new 138	java/lang/IllegalArgumentException
    //   881: dup
    //   882: new 140	java/lang/StringBuilder
    //   885: dup
    //   886: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   889: ldc_w 275
    //   892: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   895: aload_3
    //   896: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   899: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   902: ldc_w 277
    //   905: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   908: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   911: invokespecial 160	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   914: athrow
    //   915: aload 4
    //   917: invokevirtual 214	com/j256/ormlite/field/DatabaseFieldConfig:isGeneratedId	()Z
    //   920: ifeq +85 -> 1005
    //   923: aload 4
    //   925: invokevirtual 217	com/j256/ormlite/field/DatabaseFieldConfig:getGeneratedIdSequence	()Ljava/lang/String;
    //   928: ifnull +33 -> 961
    //   931: new 138	java/lang/IllegalArgumentException
    //   934: dup
    //   935: new 140	java/lang/StringBuilder
    //   938: dup
    //   939: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   942: ldc 219
    //   944: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   947: aload_3
    //   948: invokevirtual 124	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   951: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   954: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   957: invokespecial 160	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   960: athrow
    //   961: aload_0
    //   962: iconst_1
    //   963: putfield 269	com/j256/ormlite/field/FieldType:isId	Z
    //   966: aload_0
    //   967: iconst_1
    //   968: putfield 271	com/j256/ormlite/field/FieldType:isGeneratedId	Z
    //   971: aload 6
    //   973: invokeinterface 282 1 0
    //   978: ifeq +19 -> 997
    //   981: aload_0
    //   982: aload 6
    //   984: aload_2
    //   985: aload_0
    //   986: invokeinterface 286 3 0
    //   991: putfield 273	com/j256/ormlite/field/FieldType:generatedIdSequence	Ljava/lang/String;
    //   994: goto -139 -> 855
    //   997: aload_0
    //   998: aconst_null
    //   999: putfield 273	com/j256/ormlite/field/FieldType:generatedIdSequence	Ljava/lang/String;
    //   1002: goto -147 -> 855
    //   1005: aload 4
    //   1007: invokevirtual 217	com/j256/ormlite/field/DatabaseFieldConfig:getGeneratedIdSequence	()Ljava/lang/String;
    //   1010: ifnull +46 -> 1056
    //   1013: aload_0
    //   1014: iconst_1
    //   1015: putfield 269	com/j256/ormlite/field/FieldType:isId	Z
    //   1018: aload_0
    //   1019: iconst_1
    //   1020: putfield 271	com/j256/ormlite/field/FieldType:isGeneratedId	Z
    //   1023: aload 4
    //   1025: invokevirtual 217	com/j256/ormlite/field/DatabaseFieldConfig:getGeneratedIdSequence	()Ljava/lang/String;
    //   1028: astore 10
    //   1030: aload 6
    //   1032: invokeinterface 289 1 0
    //   1037: ifeq +10 -> 1047
    //   1040: aload 10
    //   1042: invokevirtual 294	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   1045: astore 10
    //   1047: aload_0
    //   1048: aload 10
    //   1050: putfield 273	com/j256/ormlite/field/FieldType:generatedIdSequence	Ljava/lang/String;
    //   1053: goto -198 -> 855
    //   1056: aload_0
    //   1057: iconst_0
    //   1058: putfield 269	com/j256/ormlite/field/FieldType:isId	Z
    //   1061: aload_0
    //   1062: iconst_0
    //   1063: putfield 271	com/j256/ormlite/field/FieldType:isGeneratedId	Z
    //   1066: aload_0
    //   1067: aconst_null
    //   1068: putfield 273	com/j256/ormlite/field/FieldType:generatedIdSequence	Ljava/lang/String;
    //   1071: goto -216 -> 855
    //   1074: aload 4
    //   1076: invokevirtual 297	com/j256/ormlite/field/DatabaseFieldConfig:isUseGetSet	()Z
    //   1079: ifeq +30 -> 1109
    //   1082: aload_0
    //   1083: aload_3
    //   1084: iconst_1
    //   1085: invokestatic 301	com/j256/ormlite/field/DatabaseFieldConfig:findGetMethod	(Ljava/lang/reflect/Field;Z)Ljava/lang/reflect/Method;
    //   1088: putfield 303	com/j256/ormlite/field/FieldType:fieldGetMethod	Ljava/lang/reflect/Method;
    //   1091: aload_0
    //   1092: aload_3
    //   1093: iconst_1
    //   1094: invokestatic 306	com/j256/ormlite/field/DatabaseFieldConfig:findSetMethod	(Ljava/lang/reflect/Field;Z)Ljava/lang/reflect/Method;
    //   1097: putfield 308	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   1100: aload_0
    //   1101: aload 6
    //   1103: aload 8
    //   1105: invokespecial 312	com/j256/ormlite/field/FieldType:assignDataType	(Lcom/j256/ormlite/db/DatabaseType;Lcom/j256/ormlite/field/DataPersister;)V
    //   1108: return
    //   1109: aload_0
    //   1110: aconst_null
    //   1111: putfield 303	com/j256/ormlite/field/FieldType:fieldGetMethod	Ljava/lang/reflect/Method;
    //   1114: aload_0
    //   1115: aconst_null
    //   1116: putfield 308	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   1119: goto -19 -> 1100
    //
    // Exception table:
    //   from	to	target	type
    //   140	153	201	java/lang/Exception
    //   153	168	229	java/lang/reflect/InvocationTargetException
    //   153	168	260	java/lang/Exception
    //   288	295	298	java/lang/Exception
  }

  private void assignDataType(DatabaseType paramDatabaseType, DataPersister paramDataPersister)
    throws SQLException
  {
    this.dataPersister = paramDataPersister;
    if (paramDataPersister == null);
    while (true)
    {
      return;
      this.fieldConverter = paramDatabaseType.getFieldConverter(paramDataPersister);
      if ((this.isGeneratedId) && (!paramDataPersister.isValidGeneratedType()))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Generated-id field '").append(this.field.getName());
        localStringBuilder.append("' in ").append(this.field.getDeclaringClass().getSimpleName());
        localStringBuilder.append(" can't be type ").append(this.dataPersister.getSqlType());
        localStringBuilder.append(".  Must be one of: ");
        for (DataType localDataType : DataType.values())
        {
          DataPersister localDataPersister = localDataType.getDataPersister();
          if ((localDataPersister == null) || (!localDataPersister.isValidGeneratedType()))
            continue;
          localStringBuilder.append(localDataType).append(' ');
        }
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      if ((this.fieldConfig.isThrowIfNull()) && (!paramDataPersister.isPrimitive()))
        throw new SQLException("Field " + this.field.getName() + " must be a primitive if set with throwIfNull");
      if ((this.isId) && (!paramDataPersister.isAppropriateId()))
        throw new SQLException("Field '" + this.field.getName() + "' is of data type " + paramDataPersister + " which cannot be the ID field");
      this.dataTypeConfigObj = paramDataPersister.makeConfigObject(this);
      String str = this.fieldConfig.getDefaultValue();
      if ((str == null) || (str.equals("")))
      {
        this.defaultValue = null;
        continue;
      }
      if (this.isGeneratedId)
        throw new SQLException("Field '" + this.field.getName() + "' cannot be a generatedId and have a default value '" + str + "'");
      this.defaultValue = this.fieldConverter.parseDefaultString(this, str);
    }
  }

  public static FieldType createFieldType(ConnectionSource paramConnectionSource, String paramString, Field paramField, Class<?> paramClass)
    throws SQLException
  {
    DatabaseFieldConfig localDatabaseFieldConfig = DatabaseFieldConfig.fromField(paramConnectionSource.getDatabaseType(), paramString, paramField);
    if (localDatabaseFieldConfig == null);
    for (FieldType localFieldType = null; ; localFieldType = new FieldType(paramConnectionSource, paramString, paramField, localDatabaseFieldConfig, paramClass))
      return localFieldType;
  }

  private LevelCounters getLevelCounters()
  {
    LevelCounters localLevelCounters = (LevelCounters)threadLevelCounters.get();
    if (localLevelCounters == null)
    {
      localLevelCounters = new LevelCounters(null);
      threadLevelCounters.set(localLevelCounters);
    }
    return localLevelCounters;
  }

  // ERROR //
  public void assignField(Object paramObject1, Object paramObject2)
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 416	com/j256/ormlite/field/FieldType:foreignIdField	Lcom/j256/ormlite/field/FieldType;
    //   4: ifnull +83 -> 87
    //   7: aload_2
    //   8: ifnull +79 -> 87
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual 420	com/j256/ormlite/field/FieldType:extractJavaFieldValue	(Ljava/lang/Object;)Ljava/lang/Object;
    //   16: astore 11
    //   18: aload 11
    //   20: ifnull +13 -> 33
    //   23: aload 11
    //   25: aload_2
    //   26: invokevirtual 421	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   29: ifeq +4 -> 33
    //   32: return
    //   33: aload_0
    //   34: invokespecial 423	com/j256/ormlite/field/FieldType:getLevelCounters	()Lcom/j256/ormlite/field/FieldType$LevelCounters;
    //   37: astore 12
    //   39: aload_0
    //   40: getfield 425	com/j256/ormlite/field/FieldType:mappedQueryForId	Lcom/j256/ormlite/stmt/mapped/MappedQueryForId;
    //   43: ifnull +18 -> 61
    //   46: aload 12
    //   48: getfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   51: aload_0
    //   52: getfield 210	com/j256/ormlite/field/FieldType:fieldConfig	Lcom/j256/ormlite/field/DatabaseFieldConfig;
    //   55: invokevirtual 432	com/j256/ormlite/field/DatabaseFieldConfig:getMaxForeignAutoRefreshLevel	()I
    //   58: if_icmplt +83 -> 141
    //   61: aload_0
    //   62: getfield 434	com/j256/ormlite/field/FieldType:foreignConstructor	Ljava/lang/reflect/Constructor;
    //   65: aload_0
    //   66: getfield 436	com/j256/ormlite/field/FieldType:foreignDao	Lcom/j256/ormlite/dao/Dao;
    //   69: invokestatic 442	com/j256/ormlite/table/TableInfo:createObject	(Ljava/lang/reflect/Constructor;Lcom/j256/ormlite/dao/Dao;)Ljava/lang/Object;
    //   72: astore 13
    //   74: aload_0
    //   75: getfield 416	com/j256/ormlite/field/FieldType:foreignIdField	Lcom/j256/ormlite/field/FieldType;
    //   78: aload 13
    //   80: aload_2
    //   81: invokevirtual 444	com/j256/ormlite/field/FieldType:assignField	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   84: aload 13
    //   86: astore_2
    //   87: aload_0
    //   88: getfield 308	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   91: ifnonnull +242 -> 333
    //   94: aload_0
    //   95: getfield 97	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   98: invokevirtual 447	java/lang/reflect/Field:isAccessible	()Z
    //   101: istore 7
    //   103: iload 7
    //   105: ifne +11 -> 116
    //   108: aload_0
    //   109: getfield 97	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   112: iconst_1
    //   113: invokevirtual 451	java/lang/reflect/Field:setAccessible	(Z)V
    //   116: aload_0
    //   117: getfield 97	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   120: aload_1
    //   121: aload_2
    //   122: invokevirtual 453	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   125: iload 7
    //   127: ifne -95 -> 32
    //   130: aload_0
    //   131: getfield 97	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   134: iconst_0
    //   135: invokevirtual 451	java/lang/reflect/Field:setAccessible	(Z)V
    //   138: goto -106 -> 32
    //   141: aload 12
    //   143: iconst_1
    //   144: aload 12
    //   146: getfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   149: iadd
    //   150: putfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   153: aload_0
    //   154: getfield 89	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   157: invokeinterface 457 1 0
    //   162: astore 15
    //   164: aload_0
    //   165: getfield 425	com/j256/ormlite/field/FieldType:mappedQueryForId	Lcom/j256/ormlite/stmt/mapped/MappedQueryForId;
    //   168: aload 15
    //   170: aload_2
    //   171: invokevirtual 463	com/j256/ormlite/stmt/mapped/MappedQueryForId:execute	(Lcom/j256/ormlite/support/DatabaseConnection;Ljava/lang/Object;)Ljava/lang/Object;
    //   174: astore 17
    //   176: aload 17
    //   178: astore 13
    //   180: aload_0
    //   181: getfield 89	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   184: aload 15
    //   186: invokeinterface 467 2 0
    //   191: aload 12
    //   193: aload 12
    //   195: getfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   198: iconst_1
    //   199: isub
    //   200: putfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   203: goto -119 -> 84
    //   206: astore 16
    //   208: aload_0
    //   209: getfield 89	com/j256/ormlite/field/FieldType:connectionSource	Lcom/j256/ormlite/support/ConnectionSource;
    //   212: aload 15
    //   214: invokeinterface 467 2 0
    //   219: aload 16
    //   221: athrow
    //   222: astore 14
    //   224: aload 12
    //   226: aload 12
    //   228: getfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   231: iconst_1
    //   232: isub
    //   233: putfield 428	com/j256/ormlite/field/FieldType$LevelCounters:autoRefreshlevel	I
    //   236: aload 14
    //   238: athrow
    //   239: astore 10
    //   241: new 140	java/lang/StringBuilder
    //   244: dup
    //   245: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   248: ldc_w 469
    //   251: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: aload_2
    //   255: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   258: ldc_w 471
    //   261: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: aload_0
    //   265: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   268: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   271: aload 10
    //   273: invokestatic 185	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   276: athrow
    //   277: astore 9
    //   279: iload 7
    //   281: ifne +11 -> 292
    //   284: aload_0
    //   285: getfield 97	com/j256/ormlite/field/FieldType:field	Ljava/lang/reflect/Field;
    //   288: iconst_0
    //   289: invokevirtual 451	java/lang/reflect/Field:setAccessible	(Z)V
    //   292: aload 9
    //   294: athrow
    //   295: astore 8
    //   297: new 140	java/lang/StringBuilder
    //   300: dup
    //   301: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   304: ldc_w 469
    //   307: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload_2
    //   311: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   314: ldc_w 471
    //   317: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: aload_0
    //   321: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   324: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   327: aload 8
    //   329: invokestatic 185	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   332: athrow
    //   333: aload_0
    //   334: getfield 308	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   337: astore 4
    //   339: iconst_1
    //   340: anewarray 4	java/lang/Object
    //   343: astore 5
    //   345: aload 5
    //   347: iconst_0
    //   348: aload_2
    //   349: aastore
    //   350: aload 4
    //   352: aload_1
    //   353: aload 5
    //   355: invokevirtual 174	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   358: pop
    //   359: goto -327 -> 32
    //   362: astore_3
    //   363: new 140	java/lang/StringBuilder
    //   366: dup
    //   367: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   370: ldc_w 473
    //   373: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload_0
    //   377: getfield 308	com/j256/ormlite/field/FieldType:fieldSetMethod	Ljava/lang/reflect/Method;
    //   380: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   383: ldc_w 475
    //   386: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload_2
    //   390: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   393: ldc_w 477
    //   396: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: aload_0
    //   400: invokevirtual 150	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   403: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: aload_3
    //   407: invokestatic 185	com/j256/ormlite/misc/SqlExceptionUtil:create	(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   410: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   164	176	206	finally
    //   153	164	222	finally
    //   180	191	222	finally
    //   208	222	222	finally
    //   116	125	239	java/lang/IllegalArgumentException
    //   116	125	277	finally
    //   241	277	277	finally
    //   297	333	277	finally
    //   116	125	295	java/lang/IllegalAccessException
    //   333	359	362	java/lang/Exception
  }

  public Object assignIdValue(Object paramObject, Number paramNumber)
    throws SQLException
  {
    Object localObject = this.dataPersister.convertIdNumber(paramNumber);
    if (localObject == null)
      throw new SQLException("Invalid class " + this.dataPersister + " for sequence-id " + this);
    assignField(paramObject, localObject);
    return localObject;
  }

  public <FT, FID> BaseForeignCollection<FT, FID> buildForeignCollection(Object paramObject, boolean paramBoolean)
    throws SQLException
  {
    Object localObject1;
    if (this.foreignFieldType == null)
      localObject1 = null;
    while (true)
    {
      return localObject1;
      Dao localDao = this.foreignDao;
      if ((!this.fieldConfig.isForeignCollectionEager()) && (!paramBoolean))
      {
        localObject1 = new LazyForeignCollection(localDao, this.foreignFieldType.dbColumnName, paramObject, this.fieldConfig.getForeignCollectionOrderColumn());
        continue;
      }
      LevelCounters localLevelCounters = getLevelCounters();
      if (localLevelCounters.foreignCollectionLevel >= this.fieldConfig.getMaxEagerForeignCollectionLevel())
      {
        localObject1 = new LazyForeignCollection(localDao, this.foreignFieldType.dbColumnName, paramObject, this.fieldConfig.getForeignCollectionOrderColumn());
        continue;
      }
      localLevelCounters.foreignCollectionLevel = (1 + localLevelCounters.foreignCollectionLevel);
      try
      {
        localObject1 = new EagerForeignCollection(localDao, this.foreignFieldType.dbColumnName, paramObject, this.fieldConfig.getForeignCollectionOrderColumn());
        localLevelCounters.foreignCollectionLevel -= 1;
      }
      finally
      {
        localLevelCounters.foreignCollectionLevel -= 1;
      }
    }
  }

  public void configDaoInformation(ConnectionSource paramConnectionSource, Class<?> paramClass)
    throws SQLException
  {
    Class localClass1 = this.field.getType();
    DatabaseType localDatabaseType = paramConnectionSource.getDatabaseType();
    Object localObject2;
    FieldType localFieldType1;
    MappedQueryForId localMappedQueryForId;
    Constructor localConstructor;
    Object localObject1;
    if (this.fieldConfig.isForeignAutoRefresh())
    {
      DatabaseTableConfig localDatabaseTableConfig3 = this.fieldConfig.getForeignTableConfig();
      if (localDatabaseTableConfig3 == null)
        localObject2 = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, localClass1);
      for (TableInfo localTableInfo3 = ((BaseDaoImpl)localObject2).getTableInfo(); ; localTableInfo3 = ((BaseDaoImpl)localObject2).getTableInfo())
      {
        localFieldType1 = localTableInfo3.getIdField();
        if (localFieldType1 != null)
          break;
        throw new IllegalArgumentException("Foreign field " + localClass1 + " does not have id field");
        localDatabaseTableConfig3.extractFieldTypes(paramConnectionSource);
        localObject2 = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, localDatabaseTableConfig3);
      }
      localMappedQueryForId = MappedQueryForId.build(localDatabaseType, localTableInfo3);
      localConstructor = localTableInfo3.getConstructor();
      localObject1 = null;
    }
    while (true)
    {
      this.mappedQueryForId = localMappedQueryForId;
      this.foreignConstructor = localConstructor;
      this.foreignFieldType = localObject1;
      this.foreignDao = ((Dao)localObject2);
      this.foreignIdField = localFieldType1;
      if (localFieldType1 != null)
        assignDataType(localDatabaseType, localFieldType1.getDataPersister());
      return;
      if (this.fieldConfig.isForeign())
      {
        if ((this.dataPersister != null) && (this.dataPersister.isPrimitive()))
          throw new IllegalArgumentException("Field " + this + " is a primitive class " + localClass1 + " but marked as foreign");
        DatabaseTableConfig localDatabaseTableConfig2 = this.fieldConfig.getForeignTableConfig();
        if (localDatabaseTableConfig2 != null)
        {
          localDatabaseTableConfig2.extractFieldTypes(paramConnectionSource);
          localObject2 = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, localDatabaseTableConfig2);
          TableInfo localTableInfo2 = ((BaseDaoImpl)localObject2).getTableInfo();
          localFieldType1 = localTableInfo2.getIdField();
          localConstructor = localTableInfo2.getConstructor();
        }
        while (localFieldType1 == null)
        {
          throw new IllegalArgumentException("Foreign field " + localClass1 + " does not have id field");
          if (BaseDaoEnabled.class.isAssignableFrom(localClass1))
          {
            localObject2 = (BaseDaoImpl)DaoManager.createDao(paramConnectionSource, localClass1);
            TableInfo localTableInfo1 = ((BaseDaoImpl)localObject2).getTableInfo();
            localFieldType1 = localTableInfo1.getIdField();
            localConstructor = localTableInfo1.getConstructor();
            continue;
          }
          localObject2 = null;
          localFieldType1 = DatabaseTableConfig.extractIdFieldType(paramConnectionSource, localClass1, DatabaseTableConfig.extractTableName(localClass1));
          localConstructor = DatabaseTableConfig.findNoArgConstructor(localClass1);
        }
        localObject1 = null;
        localMappedQueryForId = null;
        continue;
      }
      if (this.fieldConfig.isForeignCollection())
      {
        if ((localClass1 != Collection.class) && (!ForeignCollection.class.isAssignableFrom(localClass1)))
          throw new SQLException("Field class for '" + this.field.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
        Type localType = this.field.getGenericType();
        if (!(localType instanceof ParameterizedType))
          throw new SQLException("Field class for '" + this.field.getName() + "' must be a parameterized Collection.");
        Type[] arrayOfType = ((ParameterizedType)localType).getActualTypeArguments();
        if (arrayOfType.length == 0)
          throw new SQLException("Field class for '" + this.field.getName() + "' must be a parameterized Collection with at least 1 type.");
        Class localClass2 = (Class)arrayOfType[0];
        DatabaseTableConfig localDatabaseTableConfig1 = this.fieldConfig.getForeignTableConfig();
        Dao localDao;
        Object localObject3;
        FieldType[] arrayOfFieldType;
        int i;
        if (localDatabaseTableConfig1 == null)
        {
          localDao = DaoManager.createDao(paramConnectionSource, localClass2);
          localObject3 = null;
          arrayOfFieldType = ((BaseDaoImpl)localDao).getTableInfo().getFieldTypes();
          i = arrayOfFieldType.length;
        }
        for (int j = 0; ; j++)
        {
          if (j < i)
          {
            FieldType localFieldType2 = arrayOfFieldType[j];
            if (localFieldType2.getFieldType() != paramClass)
              continue;
            localObject3 = localFieldType2;
          }
          if (localObject3 != null)
            break label791;
          throw new SQLException("Foreign collection object " + localClass2 + " for field '" + this.field.getName() + "' does not contain a foreign field of class " + paramClass);
          localDao = DaoManager.createDao(paramConnectionSource, localDatabaseTableConfig1);
          break;
        }
        label791: if ((!localObject3.fieldConfig.isForeign()) && (!localObject3.fieldConfig.isForeignAutoRefresh()))
          throw new SQLException("Foreign collection object " + localClass2 + " for field '" + this.field.getName() + "' contains a field of class " + paramClass + " but it's not foreign");
        localObject2 = localDao;
        localObject1 = localObject3;
        localFieldType1 = null;
        localConstructor = null;
        localMappedQueryForId = null;
        continue;
      }
      localConstructor = null;
      localFieldType1 = null;
      localObject1 = null;
      localObject2 = null;
      localMappedQueryForId = null;
    }
  }

  public <FV> FV convertJavaFieldToSqlArgValue(Object paramObject)
    throws SQLException
  {
    if (paramObject == null);
    for (Object localObject = null; ; localObject = this.fieldConverter.javaToSqlArg(this, paramObject))
      return localObject;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (paramObject.getClass() != getClass()));
    FieldType localFieldType;
    for (boolean bool = false; ; bool = this.field.equals(localFieldType.field))
    {
      return bool;
      localFieldType = (FieldType)paramObject;
    }
  }

  public Object extractJavaFieldToSqlArgValue(Object paramObject)
    throws SQLException
  {
    return convertJavaFieldToSqlArgValue(extractJavaFieldValue(paramObject));
  }

  public <FV> FV extractJavaFieldValue(Object paramObject)
    throws SQLException
  {
    boolean bool;
    if (this.fieldGetMethod == null)
    {
      bool = this.field.isAccessible();
      if (bool);
    }
    while (true)
    {
      Object localObject2;
      try
      {
        this.field.setAccessible(true);
        Object localObject5 = this.field.get(paramObject);
        localObject2 = localObject5;
        if (bool)
          continue;
        this.field.setAccessible(false);
        if (localObject2 != null)
          break label171;
        localObject3 = null;
        return localObject3;
      }
      catch (Exception localException2)
      {
        throw SqlExceptionUtil.create("Could not get field value for " + this, localException2);
      }
      finally
      {
        if (bool)
          continue;
        this.field.setAccessible(false);
      }
      try
      {
        Object localObject1 = this.fieldGetMethod.invoke(paramObject, new Object[0]);
        localObject2 = localObject1;
      }
      catch (Exception localException1)
      {
        throw SqlExceptionUtil.create("Could not call " + this.fieldGetMethod + " for " + this, localException1);
      }
      label171: if (this.foreignIdField != null)
        localObject2 = this.foreignIdField.extractJavaFieldValue(localObject2);
      Object localObject3 = localObject2;
    }
  }

  public Object generatedId()
  {
    return this.dataPersister.generatedId();
  }

  public DataPersister getDataPersister()
  {
    return this.dataPersister;
  }

  public Object getDataTypeConfigObj()
  {
    return this.dataTypeConfigObj;
  }

  public String getDbColumnName()
  {
    return this.dbColumnName;
  }

  public Object getDefaultValue()
  {
    return this.defaultValue;
  }

  public Field getField()
  {
    return this.field;
  }

  public String getFieldName()
  {
    return this.field.getName();
  }

  public Class<?> getFieldType()
  {
    return this.field.getType();
  }

  public <FV> FV getFieldValueIfNotDefault(Object paramObject)
    throws SQLException
  {
    Object localObject1 = extractJavaFieldValue(paramObject);
    Object localObject2;
    if (localObject1 == null)
      localObject2 = null;
    while (true)
    {
      return localObject2;
      boolean bool;
      if (this.field.getType() == Boolean.TYPE)
        bool = localObject1.equals(Boolean.valueOf(DEFAULT_VALUE_BOOLEAN));
      while (true)
      {
        if (!bool)
          break label335;
        localObject2 = null;
        break;
        if ((this.field.getType() == Byte.TYPE) || (this.field.getType() == Byte.class))
        {
          bool = localObject1.equals(Byte.valueOf(DEFAULT_VALUE_BYTE));
          continue;
        }
        if ((this.field.getType() == Character.TYPE) || (this.field.getType() == Character.class))
        {
          bool = localObject1.equals(Character.valueOf(DEFAULT_VALUE_CHAR));
          continue;
        }
        if ((this.field.getType() == Short.TYPE) || (this.field.getType() == Short.class))
        {
          bool = localObject1.equals(Short.valueOf(DEFAULT_VALUE_SHORT));
          continue;
        }
        if ((this.field.getType() == Integer.TYPE) || (this.field.getType() == Integer.class))
        {
          bool = localObject1.equals(Integer.valueOf(DEFAULT_VALUE_INT));
          continue;
        }
        if ((this.field.getType() == Long.TYPE) || (this.field.getType() == Long.class))
        {
          bool = localObject1.equals(Long.valueOf(DEFAULT_VALUE_LONG));
          continue;
        }
        if ((this.field.getType() == Float.TYPE) || (this.field.getType() == Float.class))
        {
          bool = localObject1.equals(Float.valueOf(DEFAULT_VALUE_FLOAT));
          continue;
        }
        if ((this.field.getType() == Double.TYPE) || (this.field.getType() == Double.class))
        {
          bool = localObject1.equals(Double.valueOf(DEFAULT_VALUE_DOUBLE));
          continue;
        }
        bool = false;
      }
      label335: localObject2 = localObject1;
    }
  }

  public FieldType getForeignIdField()
    throws SQLException
  {
    return this.foreignIdField;
  }

  public String getFormat()
  {
    return this.fieldConfig.getFormat();
  }

  public String getGeneratedIdSequence()
  {
    return this.generatedIdSequence;
  }

  public String getIndexName()
  {
    return this.fieldConfig.getIndexName();
  }

  public SqlType getSqlType()
  {
    return this.fieldConverter.getSqlType();
  }

  public String getUniqueIndexName()
  {
    return this.fieldConfig.getUniqueIndexName();
  }

  public Enum<?> getUnknownEnumVal()
  {
    return this.fieldConfig.getUnknownEnumvalue();
  }

  public int getWidth()
  {
    return this.fieldConfig.getWidth();
  }

  public int hashCode()
  {
    return this.field.hashCode();
  }

  public boolean isCanBeNull()
  {
    return this.fieldConfig.isCanBeNull();
  }

  public boolean isComparable()
  {
    return this.dataPersister.isComparable();
  }

  public boolean isEscapedDefaultValue()
  {
    return this.dataPersister.isEscapedDefaultValue();
  }

  public boolean isEscapedValue()
  {
    return this.dataPersister.isEscapedValue();
  }

  public boolean isForeign()
  {
    return this.fieldConfig.isForeign();
  }

  public boolean isForeignCollection()
  {
    return this.fieldConfig.isForeignCollection();
  }

  public boolean isGeneratedId()
  {
    return this.isGeneratedId;
  }

  public boolean isGeneratedIdSequence()
  {
    if (this.generatedIdSequence != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isId()
  {
    return this.isId;
  }

  public boolean isSelectArgRequired()
  {
    return this.dataPersister.isSelectArgRequired();
  }

  public boolean isSelfGeneratedId()
  {
    return this.dataPersister.isSelfGeneratedId();
  }

  public boolean isUnique()
  {
    return this.fieldConfig.isUnique();
  }

  public boolean isUniqueCombo()
  {
    return this.fieldConfig.isUniqueCombo();
  }

  public <T> T resultToJava(DatabaseResults paramDatabaseResults, Map<String, Integer> paramMap)
    throws SQLException
  {
    Integer localInteger = (Integer)paramMap.get(this.dbColumnName);
    if (localInteger == null)
    {
      localInteger = Integer.valueOf(paramDatabaseResults.findColumn(this.dbColumnName));
      paramMap.put(this.dbColumnName, localInteger);
    }
    Object localObject1 = this.fieldConverter.resultToJava(this, paramDatabaseResults, localInteger.intValue());
    if (this.dataPersister.isPrimitive())
    {
      if ((this.fieldConfig.isThrowIfNull()) && (paramDatabaseResults.wasNull(localInteger.intValue())))
        throw new SQLException("Results value for primitive field '" + this.field.getName() + "' was an invalid null value");
    }
    else if ((this.fieldConverter.isStreamType()) || (!paramDatabaseResults.wasNull(localInteger.intValue())));
    for (Object localObject2 = null; ; localObject2 = localObject1)
      return localObject2;
  }

  public String toString()
  {
    return getClass().getSimpleName() + ":name=" + this.field.getName() + ",class=" + this.field.getDeclaringClass().getSimpleName();
  }

  private static class LevelCounters
  {
    int autoRefreshlevel;
    int foreignCollectionLevel;
  }
}

/* Location:           C:\Users\Егор\Downloads\APKtoJava_RC2\tools\classes-dex2jar.jar
 * Qualified Name:     com.j256.ormlite.field.FieldType
 * JD-Core Version:    0.6.0
 */