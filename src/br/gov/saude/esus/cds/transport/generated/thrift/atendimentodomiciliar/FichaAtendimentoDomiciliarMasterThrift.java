/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package br.gov.saude.esus.cds.transport.generated.thrift.atendimentodomiciliar;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-8-12")
public class FichaAtendimentoDomiciliarMasterThrift implements org.apache.thrift.TBase<FichaAtendimentoDomiciliarMasterThrift, FichaAtendimentoDomiciliarMasterThrift._Fields>, java.io.Serializable, Cloneable, Comparable<FichaAtendimentoDomiciliarMasterThrift> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FichaAtendimentoDomiciliarMasterThrift");

  private static final org.apache.thrift.protocol.TField UUID_FICHA_FIELD_DESC = new org.apache.thrift.protocol.TField("uuidFicha", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TP_CDS_ORIGEM_FIELD_DESC = new org.apache.thrift.protocol.TField("tpCdsOrigem", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField HEADER_TRANSPORT_FIELD_DESC = new org.apache.thrift.protocol.TField("headerTransport", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField ATENDIMENTOS_DOMICILIARES_FIELD_DESC = new org.apache.thrift.protocol.TField("atendimentosDomiciliares", org.apache.thrift.protocol.TType.LIST, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new FichaAtendimentoDomiciliarMasterThriftStandardSchemeFactory());
    schemes.put(TupleScheme.class, new FichaAtendimentoDomiciliarMasterThriftTupleSchemeFactory());
  }

  private String uuidFicha; // required
  private int tpCdsOrigem; // optional
  private br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift headerTransport; // required
  private List<FichaAtendimentoDomiciliarChildThrift> atendimentosDomiciliares; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    UUID_FICHA((short)1, "uuidFicha"),
    TP_CDS_ORIGEM((short)2, "tpCdsOrigem"),
    HEADER_TRANSPORT((short)3, "headerTransport"),
    ATENDIMENTOS_DOMICILIARES((short)4, "atendimentosDomiciliares");

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
        case 1: // UUID_FICHA
          return UUID_FICHA;
        case 2: // TP_CDS_ORIGEM
          return TP_CDS_ORIGEM;
        case 3: // HEADER_TRANSPORT
          return HEADER_TRANSPORT;
        case 4: // ATENDIMENTOS_DOMICILIARES
          return ATENDIMENTOS_DOMICILIARES;
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
  private static final int __TPCDSORIGEM_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.TP_CDS_ORIGEM,_Fields.ATENDIMENTOS_DOMICILIARES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.UUID_FICHA, new org.apache.thrift.meta_data.FieldMetaData("uuidFicha", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TP_CDS_ORIGEM, new org.apache.thrift.meta_data.FieldMetaData("tpCdsOrigem", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.HEADER_TRANSPORT, new org.apache.thrift.meta_data.FieldMetaData("headerTransport", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift.class)));
    tmpMap.put(_Fields.ATENDIMENTOS_DOMICILIARES, new org.apache.thrift.meta_data.FieldMetaData("atendimentosDomiciliares", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, FichaAtendimentoDomiciliarChildThrift.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FichaAtendimentoDomiciliarMasterThrift.class, metaDataMap);
  }

  public FichaAtendimentoDomiciliarMasterThrift() {
  }

  public FichaAtendimentoDomiciliarMasterThrift(
    String uuidFicha,
    br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift headerTransport)
  {
    this();
    this.uuidFicha = uuidFicha;
    this.headerTransport = headerTransport;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FichaAtendimentoDomiciliarMasterThrift(FichaAtendimentoDomiciliarMasterThrift other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetUuidFicha()) {
      this.uuidFicha = other.uuidFicha;
    }
    this.tpCdsOrigem = other.tpCdsOrigem;
    if (other.isSetHeaderTransport()) {
      this.headerTransport = new br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift(other.headerTransport);
    }
    if (other.isSetAtendimentosDomiciliares()) {
      List<FichaAtendimentoDomiciliarChildThrift> __this__atendimentosDomiciliares = new ArrayList<FichaAtendimentoDomiciliarChildThrift>(other.atendimentosDomiciliares.size());
      for (FichaAtendimentoDomiciliarChildThrift other_element : other.atendimentosDomiciliares) {
        __this__atendimentosDomiciliares.add(new FichaAtendimentoDomiciliarChildThrift(other_element));
      }
      this.atendimentosDomiciliares = __this__atendimentosDomiciliares;
    }
  }

  public FichaAtendimentoDomiciliarMasterThrift deepCopy() {
    return new FichaAtendimentoDomiciliarMasterThrift(this);
  }

  @Override
  public void clear() {
    this.uuidFicha = null;
    setTpCdsOrigemIsSet(false);
    this.tpCdsOrigem = 0;
    this.headerTransport = null;
    this.atendimentosDomiciliares = null;
  }

  public String getUuidFicha() {
    return this.uuidFicha;
  }

  public void setUuidFicha(String uuidFicha) {
    this.uuidFicha = uuidFicha;
  }

  public void unsetUuidFicha() {
    this.uuidFicha = null;
  }

  /** Returns true if field uuidFicha is set (has been assigned a value) and false otherwise */
  public boolean isSetUuidFicha() {
    return this.uuidFicha != null;
  }

  public void setUuidFichaIsSet(boolean value) {
    if (!value) {
      this.uuidFicha = null;
    }
  }

  public int getTpCdsOrigem() {
    return this.tpCdsOrigem;
  }

  public void setTpCdsOrigem(int tpCdsOrigem) {
    this.tpCdsOrigem = tpCdsOrigem;
    setTpCdsOrigemIsSet(true);
  }

  public void unsetTpCdsOrigem() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TPCDSORIGEM_ISSET_ID);
  }

  /** Returns true if field tpCdsOrigem is set (has been assigned a value) and false otherwise */
  public boolean isSetTpCdsOrigem() {
    return EncodingUtils.testBit(__isset_bitfield, __TPCDSORIGEM_ISSET_ID);
  }

  public void setTpCdsOrigemIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TPCDSORIGEM_ISSET_ID, value);
  }

  public br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift getHeaderTransport() {
    return this.headerTransport;
  }

  public void setHeaderTransport(br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift headerTransport) {
    this.headerTransport = headerTransport;
  }

  public void unsetHeaderTransport() {
    this.headerTransport = null;
  }

  /** Returns true if field headerTransport is set (has been assigned a value) and false otherwise */
  public boolean isSetHeaderTransport() {
    return this.headerTransport != null;
  }

  public void setHeaderTransportIsSet(boolean value) {
    if (!value) {
      this.headerTransport = null;
    }
  }

  public int getAtendimentosDomiciliaresSize() {
    return (this.atendimentosDomiciliares == null) ? 0 : this.atendimentosDomiciliares.size();
  }

  public java.util.Iterator<FichaAtendimentoDomiciliarChildThrift> getAtendimentosDomiciliaresIterator() {
    return (this.atendimentosDomiciliares == null) ? null : this.atendimentosDomiciliares.iterator();
  }

  public void addToAtendimentosDomiciliares(FichaAtendimentoDomiciliarChildThrift elem) {
    if (this.atendimentosDomiciliares == null) {
      this.atendimentosDomiciliares = new ArrayList<FichaAtendimentoDomiciliarChildThrift>();
    }
    this.atendimentosDomiciliares.add(elem);
  }

  public List<FichaAtendimentoDomiciliarChildThrift> getAtendimentosDomiciliares() {
    return this.atendimentosDomiciliares;
  }

  public void setAtendimentosDomiciliares(List<FichaAtendimentoDomiciliarChildThrift> atendimentosDomiciliares) {
    this.atendimentosDomiciliares = atendimentosDomiciliares;
  }

  public void unsetAtendimentosDomiciliares() {
    this.atendimentosDomiciliares = null;
  }

  /** Returns true if field atendimentosDomiciliares is set (has been assigned a value) and false otherwise */
  public boolean isSetAtendimentosDomiciliares() {
    return this.atendimentosDomiciliares != null;
  }

  public void setAtendimentosDomiciliaresIsSet(boolean value) {
    if (!value) {
      this.atendimentosDomiciliares = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case UUID_FICHA:
      if (value == null) {
        unsetUuidFicha();
      } else {
        setUuidFicha((String)value);
      }
      break;

    case TP_CDS_ORIGEM:
      if (value == null) {
        unsetTpCdsOrigem();
      } else {
        setTpCdsOrigem((Integer)value);
      }
      break;

    case HEADER_TRANSPORT:
      if (value == null) {
        unsetHeaderTransport();
      } else {
        setHeaderTransport((br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift)value);
      }
      break;

    case ATENDIMENTOS_DOMICILIARES:
      if (value == null) {
        unsetAtendimentosDomiciliares();
      } else {
        setAtendimentosDomiciliares((List<FichaAtendimentoDomiciliarChildThrift>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case UUID_FICHA:
      return getUuidFicha();

    case TP_CDS_ORIGEM:
      return Integer.valueOf(getTpCdsOrigem());

    case HEADER_TRANSPORT:
      return getHeaderTransport();

    case ATENDIMENTOS_DOMICILIARES:
      return getAtendimentosDomiciliares();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case UUID_FICHA:
      return isSetUuidFicha();
    case TP_CDS_ORIGEM:
      return isSetTpCdsOrigem();
    case HEADER_TRANSPORT:
      return isSetHeaderTransport();
    case ATENDIMENTOS_DOMICILIARES:
      return isSetAtendimentosDomiciliares();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof FichaAtendimentoDomiciliarMasterThrift)
      return this.equals((FichaAtendimentoDomiciliarMasterThrift)that);
    return false;
  }

  public boolean equals(FichaAtendimentoDomiciliarMasterThrift that) {
    if (that == null)
      return false;

    boolean this_present_uuidFicha = true && this.isSetUuidFicha();
    boolean that_present_uuidFicha = true && that.isSetUuidFicha();
    if (this_present_uuidFicha || that_present_uuidFicha) {
      if (!(this_present_uuidFicha && that_present_uuidFicha))
        return false;
      if (!this.uuidFicha.equals(that.uuidFicha))
        return false;
    }

    boolean this_present_tpCdsOrigem = true && this.isSetTpCdsOrigem();
    boolean that_present_tpCdsOrigem = true && that.isSetTpCdsOrigem();
    if (this_present_tpCdsOrigem || that_present_tpCdsOrigem) {
      if (!(this_present_tpCdsOrigem && that_present_tpCdsOrigem))
        return false;
      if (this.tpCdsOrigem != that.tpCdsOrigem)
        return false;
    }

    boolean this_present_headerTransport = true && this.isSetHeaderTransport();
    boolean that_present_headerTransport = true && that.isSetHeaderTransport();
    if (this_present_headerTransport || that_present_headerTransport) {
      if (!(this_present_headerTransport && that_present_headerTransport))
        return false;
      if (!this.headerTransport.equals(that.headerTransport))
        return false;
    }

    boolean this_present_atendimentosDomiciliares = true && this.isSetAtendimentosDomiciliares();
    boolean that_present_atendimentosDomiciliares = true && that.isSetAtendimentosDomiciliares();
    if (this_present_atendimentosDomiciliares || that_present_atendimentosDomiciliares) {
      if (!(this_present_atendimentosDomiciliares && that_present_atendimentosDomiciliares))
        return false;
      if (!this.atendimentosDomiciliares.equals(that.atendimentosDomiciliares))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_uuidFicha = true && (isSetUuidFicha());
    list.add(present_uuidFicha);
    if (present_uuidFicha)
      list.add(uuidFicha);

    boolean present_tpCdsOrigem = true && (isSetTpCdsOrigem());
    list.add(present_tpCdsOrigem);
    if (present_tpCdsOrigem)
      list.add(tpCdsOrigem);

    boolean present_headerTransport = true && (isSetHeaderTransport());
    list.add(present_headerTransport);
    if (present_headerTransport)
      list.add(headerTransport);

    boolean present_atendimentosDomiciliares = true && (isSetAtendimentosDomiciliares());
    list.add(present_atendimentosDomiciliares);
    if (present_atendimentosDomiciliares)
      list.add(atendimentosDomiciliares);

    return list.hashCode();
  }

  @Override
  public int compareTo(FichaAtendimentoDomiciliarMasterThrift other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUuidFicha()).compareTo(other.isSetUuidFicha());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUuidFicha()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uuidFicha, other.uuidFicha);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTpCdsOrigem()).compareTo(other.isSetTpCdsOrigem());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTpCdsOrigem()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tpCdsOrigem, other.tpCdsOrigem);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHeaderTransport()).compareTo(other.isSetHeaderTransport());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeaderTransport()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.headerTransport, other.headerTransport);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAtendimentosDomiciliares()).compareTo(other.isSetAtendimentosDomiciliares());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAtendimentosDomiciliares()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.atendimentosDomiciliares, other.atendimentosDomiciliares);
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
    StringBuilder sb = new StringBuilder("FichaAtendimentoDomiciliarMasterThrift(");
    boolean first = true;

    sb.append("uuidFicha:");
    if (this.uuidFicha == null) {
      sb.append("null");
    } else {
      sb.append(this.uuidFicha);
    }
    first = false;
    if (isSetTpCdsOrigem()) {
      if (!first) sb.append(", ");
      sb.append("tpCdsOrigem:");
      sb.append(this.tpCdsOrigem);
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("headerTransport:");
    if (this.headerTransport == null) {
      sb.append("null");
    } else {
      sb.append(this.headerTransport);
    }
    first = false;
    if (isSetAtendimentosDomiciliares()) {
      if (!first) sb.append(", ");
      sb.append("atendimentosDomiciliares:");
      if (this.atendimentosDomiciliares == null) {
        sb.append("null");
      } else {
        sb.append(this.atendimentosDomiciliares);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetUuidFicha()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uuidFicha' is unset! Struct:" + toString());
    }

    if (!isSetHeaderTransport()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'headerTransport' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
    if (headerTransport != null) {
      headerTransport.validate();
    }
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

  private static class FichaAtendimentoDomiciliarMasterThriftStandardSchemeFactory implements SchemeFactory {
    public FichaAtendimentoDomiciliarMasterThriftStandardScheme getScheme() {
      return new FichaAtendimentoDomiciliarMasterThriftStandardScheme();
    }
  }

  private static class FichaAtendimentoDomiciliarMasterThriftStandardScheme extends StandardScheme<FichaAtendimentoDomiciliarMasterThrift> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FichaAtendimentoDomiciliarMasterThrift struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // UUID_FICHA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uuidFicha = iprot.readString();
              struct.setUuidFichaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TP_CDS_ORIGEM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tpCdsOrigem = iprot.readI32();
              struct.setTpCdsOrigemIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HEADER_TRANSPORT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.headerTransport = new br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift();
              struct.headerTransport.read(iprot);
              struct.setHeaderTransportIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ATENDIMENTOS_DOMICILIARES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list24 = iprot.readListBegin();
                struct.atendimentosDomiciliares = new ArrayList<FichaAtendimentoDomiciliarChildThrift>(_list24.size);
                FichaAtendimentoDomiciliarChildThrift _elem25;
                for (int _i26 = 0; _i26 < _list24.size; ++_i26)
                {
                  _elem25 = new FichaAtendimentoDomiciliarChildThrift();
                  _elem25.read(iprot);
                  struct.atendimentosDomiciliares.add(_elem25);
                }
                iprot.readListEnd();
              }
              struct.setAtendimentosDomiciliaresIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, FichaAtendimentoDomiciliarMasterThrift struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.uuidFicha != null) {
        oprot.writeFieldBegin(UUID_FICHA_FIELD_DESC);
        oprot.writeString(struct.uuidFicha);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTpCdsOrigem()) {
        oprot.writeFieldBegin(TP_CDS_ORIGEM_FIELD_DESC);
        oprot.writeI32(struct.tpCdsOrigem);
        oprot.writeFieldEnd();
      }
      if (struct.headerTransport != null) {
        oprot.writeFieldBegin(HEADER_TRANSPORT_FIELD_DESC);
        struct.headerTransport.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.atendimentosDomiciliares != null) {
        if (struct.isSetAtendimentosDomiciliares()) {
          oprot.writeFieldBegin(ATENDIMENTOS_DOMICILIARES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.atendimentosDomiciliares.size()));
            for (FichaAtendimentoDomiciliarChildThrift _iter27 : struct.atendimentosDomiciliares)
            {
              _iter27.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FichaAtendimentoDomiciliarMasterThriftTupleSchemeFactory implements SchemeFactory {
    public FichaAtendimentoDomiciliarMasterThriftTupleScheme getScheme() {
      return new FichaAtendimentoDomiciliarMasterThriftTupleScheme();
    }
  }

  private static class FichaAtendimentoDomiciliarMasterThriftTupleScheme extends TupleScheme<FichaAtendimentoDomiciliarMasterThrift> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FichaAtendimentoDomiciliarMasterThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.uuidFicha);
      struct.headerTransport.write(oprot);
      BitSet optionals = new BitSet();
      if (struct.isSetTpCdsOrigem()) {
        optionals.set(0);
      }
      if (struct.isSetAtendimentosDomiciliares()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetTpCdsOrigem()) {
        oprot.writeI32(struct.tpCdsOrigem);
      }
      if (struct.isSetAtendimentosDomiciliares()) {
        {
          oprot.writeI32(struct.atendimentosDomiciliares.size());
          for (FichaAtendimentoDomiciliarChildThrift _iter28 : struct.atendimentosDomiciliares)
          {
            _iter28.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FichaAtendimentoDomiciliarMasterThrift struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.uuidFicha = iprot.readString();
      struct.setUuidFichaIsSet(true);
      struct.headerTransport = new br.gov.saude.esus.cds.transport.generated.thrift.common.UnicaLotacaoHeaderThrift();
      struct.headerTransport.read(iprot);
      struct.setHeaderTransportIsSet(true);
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.tpCdsOrigem = iprot.readI32();
        struct.setTpCdsOrigemIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list29 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.atendimentosDomiciliares = new ArrayList<FichaAtendimentoDomiciliarChildThrift>(_list29.size);
          FichaAtendimentoDomiciliarChildThrift _elem30;
          for (int _i31 = 0; _i31 < _list29.size; ++_i31)
          {
            _elem30 = new FichaAtendimentoDomiciliarChildThrift();
            _elem30.read(iprot);
            struct.atendimentosDomiciliares.add(_elem30);
          }
        }
        struct.setAtendimentosDomiciliaresIsSet(true);
      }
    }
  }

}

