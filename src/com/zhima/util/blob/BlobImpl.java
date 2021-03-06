
package com.zhima.util.blob;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobImpl implements Blob {
	private InputStream stream;
	private int length;
	private boolean needsReset = false;
	public BlobImpl(byte[] bytes) {
		this.stream = new ByteArrayInputStream(bytes);
		this.length = bytes.length;
	}
	public BlobImpl(InputStream stream, int length) {
		this.stream = stream;
		this.length = length;
	}

	/**
	 * 得到长度
	 */
	public long length() throws SQLException {
		return length;
	}

	/**
	 * @see java.sql.Blob#truncate(long)
	 */
	public void truncate(long pos) throws SQLException {
		excep();
	}

	/**
	 * @see java.sql.Blob#getBytes(long, int)
	 */
	public byte[] getBytes(long pos, int len) throws SQLException {
		excep(); return null;
	}

	/**
	 * @see java.sql.Blob#setBytes(long, byte[])
	 */
	public int setBytes(long pos, byte[] bytes) throws SQLException {
		excep(); return 0;
	}

	/**
	 * @see java.sql.Blob#setBytes(long, byte[], int, int)
	 */
	public int setBytes(long pos, byte[] bytes, int i, int j)
	throws SQLException {
		excep(); return 0;
	}

	/**
	 * @see java.sql.Blob#position(byte[], long)
	 */
	public long position(byte[] bytes, long pos) throws SQLException {
		excep(); return 0;
	}

	/**
	 * @see java.sql.Blob#getBinaryStream()
	 */
	public InputStream getBinaryStream() throws SQLException {
		try {
			if (needsReset) stream.reset();
		}
		catch (IOException ioe) {
			throw new SQLException("could not reset reader");
		}
		needsReset = true;
		return stream;
	}

	/**
	 * @see java.sql.Blob#setBinaryStream(long)
	 */
	public OutputStream setBinaryStream(long pos) throws SQLException {
		excep(); return null;
	}

	/**
	 * @see java.sql.Blob#position(Blob, long)
	 */
	public long position(Blob blob, long pos) throws SQLException {
		excep(); return 0;
	}

	private static void excep() {
		throw new UnsupportedOperationException("类型不能被创建！");
	}
	@Override
	public void free() throws SQLException {
		
	}
	@Override
	public InputStream getBinaryStream(long arg0, long arg1)
			throws SQLException {
		return null;
	}

}
