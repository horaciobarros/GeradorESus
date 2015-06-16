package esaude.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Util {

	public static byte[] serialize(TBase<?, ? extends TFieldIdEnum> thrift)
			throws TException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TIOStreamTransport transport = new TIOStreamTransport(baos);
		TBinaryProtocol protocol = new TBinaryProtocol(transport);
		thrift.write(protocol);
		return baos.toByteArray();
	}

	public static void unserialize(byte[] data,
			TBase<?, ? extends TFieldIdEnum> thrift) throws TException {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		TIOStreamTransport transport = new TIOStreamTransport(bais);
		TBinaryProtocol protocol = new TBinaryProtocol(transport);
		thrift.read(protocol);
	}

	public static Long castToLong(Object value, Long defaultValue) {
		if (value != null) {
			if (value instanceof Long) {
				return (Long) value;
			} else if (value instanceof Number) {
				return new Long(((Number) value).longValue());
			} else if (value instanceof String) {
				try {
					return value.equals("") ? defaultValue : new Long(
							(String) value);
				} catch (NumberFormatException exn) {
					return defaultValue;
				}
			} else if (value instanceof BigInteger) {
				return ((BigInteger) value).longValue();
			} else if (value instanceof BigDecimal) {
				return ((BigDecimal) value).longValue();
			}
		}
		return defaultValue;
	}

	public static Long castToLong(Object value) {
		return castToLong(value, null);
	}

	public static boolean isBlank(String text) {
		if (text != null && text.length() > 0) {
			for (int i = 0, iSize = text.length(); i < iSize; i++) {
				if (text.charAt(i) != ' ') {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isNotBlank(String nome) {
		return !isBlank(nome);
	}

	public static Integer castToInteger(Object value, Integer defaultValue) {
		if (value != null) {
			if (value instanceof Integer) {
				return (Integer) value;
			} else if (value instanceof Number) {
				return new Integer(((Number) value).intValue());
			} else if (value instanceof BigInteger) {
				return ((BigInteger) value).intValue();
			} else if (value instanceof BigDecimal) {
				return ((BigDecimal) value).intValue();
			} else if (value instanceof String) {
				try {
					return value.equals("") ? defaultValue : new Integer(
							(String) value);
				} catch (NumberFormatException exn) {
					return defaultValue;
				}
			}
		}
		return defaultValue;
	}

	public static Integer castToInteger(Object value) {
		return castToInteger(value, null);
	}

	public static SessionFactory getSessionFactory() {
		AnnotationConfiguration configuracao = new AnnotationConfiguration().configure();
		return configuracao.buildSessionFactory();
	}
}
