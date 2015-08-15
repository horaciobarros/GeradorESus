/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package br.gov.saude.esus.cds.transport.generated.thrift.atividadecoletiva;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-6-16")
public class ParticipanteRowItemThrift implements org.apache.thrift.TBase<ParticipanteRowItemThrift, ParticipanteRowItemThrift._Fields>, java.io.Serializable, Cloneable, Comparable<ParticipanteRowItemThrift> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ParticipanteRowItemThrift");

  private static final org.apache.thrift.protocol.TField CNS_FIELD_DESC = new org.apache.thrift.protocol.TField("cns", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DATA_NASCIMENTO_FIELD_DESC = new org.apache.thrift.protocol.TField("dataNascimento", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField AVALIACAO_ALTERADA_FIELD_DESC = new org.apache.thrift.protocol.TField("avaliacaoAlterada", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField PESO_FIELD_DESC = new org.apache.thrift.protocol.TField("peso", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField ALTURA_FIELD_DESC = new org.apache.thrift.protocol.TField("altura", org.apache.thrift.protocol.TType.DOUBLE, (short)5);
  private static final org.apache.thrift.protocol.TField CESSOU_HABITO_FUMAR_FIELD_DESC = new org.apache.thrift.protocol.TField("cessouHabitoFumar", org.apache.thrift.protocol.TType.BOOL, (short)6);
  private static final org.apache.thrift.protocol.TField ABANDONOU_GRUPO_FIELD_DESC = new org.apache.thrift.protocol.TField("abandonouGrupo", org.apache.thrift.protocol.TType.BOOL, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ParticipanteRowItemThriftStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ParticipanteRowItemThriftTupleSchemeFactory());
  }

  private String cns; // optional
  private long dataNascimento; // optional
  private boolean avaliacaoAlterada; // optional
  private double peso; // optional
  private double altura; // optional
  private boolean cessouHabitoFumar; // optional
  private boolean abandonouGrupo; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CNS((short)1, "cns"),
    DATA_NASCIMENTO((short)2, "dataNascimento"),
    AVALIACAO_ALTERADA((short)3, "avaliacaoAlterada"),
    PESO((short)4, "peso"),
    ALTURA((short)5, "altura"),
    CESSOU_HABITO_FUMAR((short)6, "cessouHabitoFumar"),
    ABANDONOU_GRUPO((short)7, "abandonouGrupo");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CNS
          return CNS;
        case 2: // DATA_NASCIMENTO
          return DATA_NASCIMENTO;
        case 3: // AVALIACAO_ALTERADA
          return AVALIACAO_ALTERADA;
        case 4: // PESO
          return PESO;
        case 5: // ALTURA
          return ALTURA;
        case 6: // CESSOU_HABITO_FUMAR
          return CESSOU_HABITO_FUMAR;
        case 7: // ABANDONOU_GRUPO
          return ABANDONOU_GRUPO;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DATANASCIMENTO_ISSET_ID = 0;
  private static final int __AVALIACAOALTERADA_ISSET_ID = 1;
  private static final int __PESO_ISSET_ID = 2;
  private static final int __ALTURA_ISSET_ID = 3;
  private static final int __CESSOUHABITOFUMAR_ISSET_ID = 4;
  private static final int __ABANDONOUGRUPO_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.CNS,_Fields.DATA_NASCIMENTO,_Fields.AVALIACAO_ALTERADA,_Fields.PESO,_Fields.ALTURA,_Fields.CESSOU_HABITO_FUMAR,_Fields.ABANDONOU_GRUPO};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CNS, new org.apache.thrift.meta_data.FieldMetaData("cns", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATA_NASCIMENTO, new org.apache.thrift.meta_data.FieldMetaData("dataNascimento", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.AVALIACAO_ALTERADA, new org.apache.thrift.meta_data.FieldMetaData("avaliacaoAlterada", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.PESO, new org.apache.thrift.meta_data.FieldMetaData("peso", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.ALTURA, new org.apache.thrift.meta_data.FieldMetaData("altura", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.CESSOU_HABITO_FUMAR, new org.apache.thrift.meta_data.FieldMetaData("cessouHabitoFumar", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ABANDONOU_GRUPO, new org.apache.thrift.meta_data.FieldMetaData("abandonouGrupo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ParticipanteRowItemThrift.class, metaDataMap);
  }

  public ParticipanteRowItemThrift() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ParticipanteRowItemThrift(ParticipanteRowItemThrift other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCns()) {
      this.cns = other.cns;
    }
    this.dataNascimento = other.dataNascimento;
    this.avaliacaoAlterada = other.avaliacaoAlterada;
    this.peso = other.peso;
    this.altura = other.altura;
    this.cessouHabitoFumar = other.cessouHabitoFumar;
    this.abandonouGrupo = other.abandonouGrupo;
  }

  public ParticipanteRowItemThrift deepCopy() {
    return new ParticipanteRowItemThrift(this);
  }

  @Override
  public void clear() {
    this.cns = null;
    setDataNascimentoIsSet(false);
    this.dataNascimento = 0;
    setAvaliacaoAlteradaIsSet(false);
    this.avaliacaoAlterada = false;
    setPesoIsSet(false);
    this.peso = 0.0;
    setAlturaIsSet(false);
    this.altura = 0.0;
    setCessouHabitoFumarIsSet(false);
    this.cessouHabitoFumar = false;
    setAbandonouGrupoIsSet(false);
    this.abandonouGrupo = false;
  }

  public String getCns() {
    return this.cns;
  }

  public void setCns(String cns) {
    this.cns = cns;
  }

  public void unsetCns() {
    this.cns = null;
  }

  /** Returns true if field cns is set (has been assigned a value) and false otherwise */
  public boolean isSetCns() {
    return this.cns != null;
  }

  public void setCnsIsSet(boolean value) {
    if (!value) {
      this.cns = null;
    }
  }

  public long getDataNascimento() {
    return this.dataNascimento;
  }

  public void setDataNascimento(long dataNascimento) {
    this.dataNascimento = dataNascimento;
    setDataNascimentoIsSet(true);
  }

  public void unsetDataNascimento() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DATANASCIMENTO_ISSET_ID);
  }

  /** Returns true if field dataNascimento is set (has been assigned a value) and false otherwise */
  public boolean isSetDataNascimento() {
    return EncodingUtils.testBit(__isset_bitfield, __DATANASCIMENTO_ISSET_ID);
  }

  public void setDataNascimentoIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DATANASCIMENTO_ISSET_ID, value);
  }

  public boolean isAvaliacaoAlterada() {
    return this.avaliacaoAlterada;
  }

  public void setAvaliacaoAlterada(boolean avaliacaoAlterada) {
    this.avaliacaoAlterada = avaliacaoAlterada;
    setAvaliacaoAlteradaIsSet(true);
  }

  public void unsetAvaliacaoAlterada() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __AVALIACAOALTERADA_ISSET_ID);
  }

  /** Returns true if field avaliacaoAlterada is set (has been assigned a value) and false otherwise */
  public boolean isSetAvaliacaoAlterada() {
    return EncodingUtils.testBit(__isset_bitfield, __AVALIACAOALTERADA_ISSET_ID);
  }

  public void setAvaliacaoAlteradaIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __AVALIACAOALTERADA_ISSET_ID, value);
  }

  public double getPeso() {
    return this.peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
    setPesoIsSet(true);
  }

  public void unsetPeso() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PESO_ISSET_ID);
  }

  /** Returns true if field peso is set (has been assigned a value) and false otherwise */
  public boolean isSetPeso() {
    return EncodingUtils.testBit(__isset_bitfield, __PESO_ISSET_ID);
  }

  public void setPesoIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PESO_ISSET_ID, value);
  }

  public double getAltura() {
    return this.altura;
  }

  public void setAltura(double altura) {
    this.altura = altura;
    setAlturaIsSet(true);
  }

  public void unsetAltura() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ALTURA_ISSET_ID);
  }

  /** Returns true if field altura is set (has been assigned a value) and false otherwise */
  public boolean isSetAltura() {
    return EncodingUtils.testBit(__isset_bitfield, __ALTURA_ISSET_ID);
  }

  public void setAlturaIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ALTURA_ISSET_ID, value);
  }

  public boolean isCessouHabitoFumar() {
    return this.cessouHabitoFumar;
  }

  public void setCessouHabitoFumar(boolean cessouHabitoFumar) {
    this.cessouHabitoFumar = cessouHabitoFumar;
    setCessouHabitoFumarIsSet(true);
  }

  public void unsetCessouHabitoFumar() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CESSOUHABITOFUMAR_ISSET_ID);
  }

  /** Returns true if field cessouHabitoFumar is set (has been assigned a value) and false otherwise */
  public boolean isSetCessouHabitoFumar() {
    return EncodingUtils.testBit(__isset_bitfield, __CESSOUHABITOFUMAR_ISSET_ID);
  }

  public void setCessouHabitoFumarIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CESSOUHABITOFUMAR_ISSET_ID, value);
  }

  public boolean isAbandonouGrupo() {
    return this.abandonouGrupo;
  }

  public void setAbandonouGrupo(boolean abandonouGrupo) {
    this.abandonouGrupo = abandonouGrupo;
    setAbandonouGrupoIsSet(true);
  }

  public void unsetAbandonouGrupo() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ABANDONOUGRUPO_ISSET_ID);
  }

  /** Returns true if field abandonouGrupo is set (has been assigned a value) and false otherwise */
  public boolean isSetAbandonouGrupo() {
    return EncodingUtils.testBit(__isset_bitfield, __ABANDONOUGRUPO_ISSET_ID);
  }

  public void setAbandonouGrupoIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ABANDONOUGRUPO_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CNS:
      if (value == null) {
        unsetCns();
      } else {
        setCns((String)value);
      }
      break;

    case DATA_NASCIMENTO:
      if (value == null) {
        unsetDataNascimento();
      } else {
        setDataNascimento((Long)value);
      }
      break;

    case AVALIACAO_ALTERADA:
      if (value == null) {
        unsetAvaliacaoAlterada();
      } else {
        setAvaliacaoAlterada((Boolean)value);
      }
      break;

    case PESO:
      if (value == null) {
        unsetPeso();
      } else {
        setPeso((Double)value);
      }
      break;

    case ALTURA:
      if (value == null) {
        unsetAltura();
      } else {
        setAltura((Double)value);
      }
      break;

    case CESSOU_HABITO_FUMAR:
      if (value == null) {
        unsetCessouHabitoFumar();
      } else {
        setCessouHabitoFumar((Boolean)value);
      }
      break;

    case ABANDONOU_GRUPO:
      if (value == null) {
        unsetAbandonouGrupo();
      } else {
        setAbandonouGrupo((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CNS:
      return getCns();

    case DATA_NASCIMENTO:
      return Long.valueOf(getDataNascimento());

    case AVALIACAO_ALTERADA:
      return Boolean.valueOf(isAvaliacaoAlterada());

    case PESO:
      return Double.valueOf(getPeso());

    case ALTURA:
      return Double.valueOf(getAltura());

    case CESSOU_HABITO_FUMAR:
      return Boolean.valueOf(isCessouHabitoFumar());

    case ABANDONOU_GRUPO:
      return Boolean.valueOf(isAbandonouGrupo());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CNS:
      return isSetCns();
    case DATA_NASCIMENTO:
      return isSetDataNascimento();
    case AVALIACAO_ALTERADA:
      return isSetAvaliacaoAlterada();
    case PESO:
      return isSetPeso();
    case ALTURA:
      return isSetAltura();
    case CESSOU_HABITO_FUMAR:
      return isSetCessouHabitoFumar();
    case ABANDONOU_GRUPO:
      return isSetAbandonouGrupo();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ParticipanteRowItemThrift)
      return this.equals((ParticipanteRowItemThrift)that);
    return false;
  }

  public boolean equals(ParticipanteRowItemThrift that) {
    if (that == null)
      return false;

    boolean this_present_cns = true && this.isSetCns();
    boolean that_present_cns = true && that.isSetCns();
    if (this_present_cns || that_present_cns) {
      if (!(this_present_cns && that_present_cns))
        return false;
      if (!this.cns.equals(that.cns))
        return false;
    }

    boolean this_present_dataNascimento = true && this.isSetDataNascimento();
    boolean that_present_dataNascimento = true && that.isSetDataNascimento();
    if (this_present_dataNascimento || that_present_dataNascimento) {
      if (!(this_present_dataNascimento && that_present_dataNascimento))
        return false;
      if (this.dataNascimento != that.dataNascimento)
        return false;
    }

    boolean this_present_avaliacaoAlterada = true && this.isSetAvaliacaoAlterada();
    boolean that_present_avaliacaoAlterada = true && that.isSetAvaliacaoAlterada();
    if (this_present_avaliacaoAlterada || that_present_avaliacaoAlterada) {
      if (!(this_present_avaliacaoAlterada && that_present_avaliacaoAlterada))
        return false;
      if (this.avaliacaoAlterada != that.avaliacaoAlterada)
        return false;
    }

    boolean this_present_peso = true && this.isSetPeso();
    boolean that_present_peso = true && that.isSetPeso();
    if (this_present_peso || that_present_peso) {
      if (!(this_present_peso && that_present_peso))
        return false;
      if (this.peso != that.peso)
        return false;
    }

    boolean this_present_altura = true && this.isSetAltura();
    boolean that_present_altura = true && that.isSetAltura();
    if (this_present_altura || that_present_altura) {
      if (!(this_present_altura && that_present_altura))
        return false;
      if (this.altura != that.altura)
        return false;
    }

    boolean this_present_cessouHabitoFumar = true && this.isSetCessouHabitoFumar();
    boolean that_present_cessouHabitoFumar = true && that.isSetCessouHabitoFumar();
    if (this_present_cessouHabitoFumar || that_present_cessouHabitoFumar) {
      if (!(this_present_cessouHabitoFumar && that_present_cessouHabitoFumar))
        return false;
      if (this.cessouHabitoFumar != that.cessouHabitoFumar)
        return false;
    }

    boolean this_present_abandonouGrupo = true && this.isSetAbandonouGrupo();
    boolean that_present_abandonouGrupo = true && that.isSetAbandonouGrupo();
    if (this_present_abandonouGrupo || that_present_abandonouGrupo) {
      if (!(this_present_abandonouGrupo && that_present_abandonouGrupo))
        return false;
      if (this.abandonouGrupo != that.abandonouGrupo)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_cns = true && (isSetCns());
    list.add(present_cns);
    if (present_cns)
      list.add(cns);

    boolean present_dataNascimento = true && (isSetDataNascimento());
    list.add(present_dataNascimento);
    if (present_dataNascimento)
      list.add(dataNascimento);

    boolean present_avaliacaoAlterada = true && (isSetAvaliacaoAlterada());
    list.add(present_avaliacaoAlterada);
    if (present_avaliacaoAlterada)
      list.add(avaliacaoAlterada);

    boolean present_peso = true && (isSetPeso());
    list.add(present_peso);
    if (present_peso)
      list.add(peso);

    boolean present_altura = true && (isSetAltura());
    list.add(present_altura);
    if (present_altura)
      list.add(altura);

    boolean present_cessouHabitoFumar = true && (isSetCessouHabitoFumar());
    list.add(present_cessouHabitoFumar);
    if (present_cessouHabitoFumar)
      list.add(cessouHabitoFumar);

    boolean present_abandonouGrupo = true && (isSetAbandonouGrupo());
    list.add(present_abandonouGrupo);
    if (present_abandonouGrupo)
      list.add(abandonouGrupo);

    return list.hashCode();
  }

  @Override
  public int compareTo(ParticipanteRowItemThrift other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCns()).compareTo(other.isSetCns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cns, other.cns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDataNascimento()).compareTo(other.isSetDataNascimento());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataNascimento()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataNascimento, other.dataNascimento);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAvaliacaoAlterada()).compareTo(other.isSetAvaliacaoAlterada());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAvaliacaoAlterada()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.avaliacaoAlterada, other.avaliacaoAlterada);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPeso()).compareTo(other.isSetPeso());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPeso()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.peso, other.peso);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAltura()).compareTo(other.isSetAltura());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAltura()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.altura, other.altura);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCessouHabitoFumar()).compareTo(other.isSetCessouHabitoFumar());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCessouHabitoFumar()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cessouHabitoFumar, other.cessouHabitoFumar);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAbandonouGrupo()).compareTo(other.isSetAbandonouGrupo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAbandonouGrupo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.abandonouGrupo, other.abandonouGrupo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ParticipanteRowItemThrift(");
    boolean first = true;

    if (isSetCns()) {
      sb.append("cns:");
      if (this.cns == null) {
        sb.append("null");
      } else {
        sb.append(this.cns);
      }
      first = false;
    }
    if (isSetDataNascimento()) {
      if (!first) sb.append(", ");
      sb.append("dataNascimento:");
      sb.append(this.dataNascimento);
      first = false;
    }
    if (isSetAvaliacaoAlterada()) {
      if (!first) sb.append(", ");
      sb.append("avaliacaoAlterada:");
      sb.append(this.avaliacaoAlterada);
      first = false;
    }
    if (isSetPeso()) {
      if (!first) sb.append(", ");
      sb.append("peso:");
      sb.append(this.peso);
      first = false;
    }
    if (isSetAltura()) {
      if (!first) sb.append(", ");
      sb.append("altura:");
      sb.append(this.altura);
      first = false;
    }
    if (isSetCessouHabitoFumar()) {
      if (!first) sb.append(", ");
      sb.append("cessouHabitoFumar:");
      sb.append(this.cessouHabitoFumar);
      first = false;
    }
    if (isSetAbandonouGrupo()) {
      if (!first) sb.append(", ");
      sb.append("abandonouGrupo:");
      sb.append(this.abandonouGrupo);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ParticipanteRowItemThriftStandardSchemeFactory implements SchemeFactory {
    public ParticipanteRowItemThriftStandardScheme getScheme() {
      return new ParticipanteRowItemThriftStandardScheme();
    }
  }

  private static class ParticipanteRowItemThriftStandardScheme extends StandardScheme<ParticipanteRowItemThrift> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ParticipanteRowItemThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CNS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.cns = iprot.readString();
              struct.setCnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DATA_NASCIMENTO
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.dataNascimento = iprot.readI64();
              struct.setDataNascimentoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // AVALIACAO_ALTERADA
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.avaliacaoAlterada = iprot.readBool();
              struct.setAvaliacaoAlteradaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PESO
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.peso = iprot.readDouble();
              struct.setPesoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ALTURA
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.altura = iprot.readDouble();
              struct.setAlturaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CESSOU_HABITO_FUMAR
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.cessouHabitoFumar = iprot.readBool();
              struct.setCessouHabitoFumarIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // ABANDONOU_GRUPO
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.abandonouGrupo = iprot.readBool();
              struct.setAbandonouGrupoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ParticipanteRowItemThrift struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.cns != null) {
        if (struct.isSetCns()) {
          oprot.writeFieldBegin(CNS_FIELD_DESC);
          oprot.writeString(struct.cns);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetDataNascimento()) {
        oprot.writeFieldBegin(DATA_NASCIMENTO_FIELD_DESC);
        oprot.writeI64(struct.dataNascimento);
        oprot.writeFieldEnd();
      }
      if (struct.isSetAvaliacaoAlterada()) {
        oprot.writeFieldBegin(AVALIACAO_ALTERADA_FIELD_DESC);
        oprot.writeBool(struct.avaliacaoAlterada);
        oprot.writeFieldEnd();
      }
      if (struct.isSetPeso()) {
        oprot.writeFieldBegin(PESO_FIELD_DESC);
        oprot.writeDouble(struct.peso);
        oprot.writeFieldEnd();
      }
      if (struct.isSetAltura()) {
        oprot.writeFieldBegin(ALTURA_FIELD_DESC);
        oprot.writeDouble(struct.altura);
        oprot.writeFieldEnd();
      }
      if (struct.isSetCessouHabitoFumar()) {
        oprot.writeFieldBegin(CESSOU_HABITO_FUMAR_FIELD_DESC);
        oprot.writeBool(struct.cessouHabitoFumar);
        oprot.writeFieldEnd();
      }
      if (struct.isSetAbandonouGrupo()) {
        oprot.writeFieldBegin(ABANDONOU_GRUPO_FIELD_DESC);
        oprot.writeBool(struct.abandonouGrupo);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ParticipanteRowItemThriftTupleSchemeFactory implements SchemeFactory {
    public ParticipanteRowItemThriftTupleScheme getScheme() {
      return new ParticipanteRowItemThriftTupleScheme();
    }
  }

  private static class ParticipanteRowItemThriftTupleScheme extends TupleScheme<ParticipanteRowItemThrift> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ParticipanteRowItemThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCns()) {
        optionals.set(0);
      }
      if (struct.isSetDataNascimento()) {
        optionals.set(1);
      }
      if (struct.isSetAvaliacaoAlterada()) {
        optionals.set(2);
      }
      if (struct.isSetPeso()) {
        optionals.set(3);
      }
      if (struct.isSetAltura()) {
        optionals.set(4);
      }
      if (struct.isSetCessouHabitoFumar()) {
        optionals.set(5);
      }
      if (struct.isSetAbandonouGrupo()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetCns()) {
        oprot.writeString(struct.cns);
      }
      if (struct.isSetDataNascimento()) {
        oprot.writeI64(struct.dataNascimento);
      }
      if (struct.isSetAvaliacaoAlterada()) {
        oprot.writeBool(struct.avaliacaoAlterada);
      }
      if (struct.isSetPeso()) {
        oprot.writeDouble(struct.peso);
      }
      if (struct.isSetAltura()) {
        oprot.writeDouble(struct.altura);
      }
      if (struct.isSetCessouHabitoFumar()) {
        oprot.writeBool(struct.cessouHabitoFumar);
      }
      if (struct.isSetAbandonouGrupo()) {
        oprot.writeBool(struct.abandonouGrupo);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ParticipanteRowItemThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.cns = iprot.readString();
        struct.setCnsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.dataNascimento = iprot.readI64();
        struct.setDataNascimentoIsSet(true);
      }
      if (incoming.get(2)) {
        struct.avaliacaoAlterada = iprot.readBool();
        struct.setAvaliacaoAlteradaIsSet(true);
      }
      if (incoming.get(3)) {
        struct.peso = iprot.readDouble();
        struct.setPesoIsSet(true);
      }
      if (incoming.get(4)) {
        struct.altura = iprot.readDouble();
        struct.setAlturaIsSet(true);
      }
      if (incoming.get(5)) {
        struct.cessouHabitoFumar = iprot.readBool();
        struct.setCessouHabitoFumarIsSet(true);
      }
      if (incoming.get(6)) {
        struct.abandonouGrupo = iprot.readBool();
        struct.setAbandonouGrupoIsSet(true);
      }
    }
  }

}

