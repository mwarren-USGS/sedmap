package gov.cida.sedmap.data;

import java.sql.SQLException;
import java.util.List;

import gov.cida.sedmap.io.IoUtils;

public class RdbFormatter extends CharSepFormatter {

	// TODO this file is currently empty
	public static final String RDB_FILE_HEADER = "/rdb-header.txt";


	public RdbFormatter() {
		super("text/tab-separated-values", "\t", "rdb");
	}



	public String getFileHeader(){
		return IoUtils.readTextResource(RDB_FILE_HEADER);
	}


	@Override
	public String fileHeader(List<Column> columns) throws SQLException {
		StringBuilder header = new StringBuilder( getFileHeader() );

		if ( ! header.toString().endsWith(IoUtils.LINE_SEPARATOR) ) {
			header.append(IoUtils.LINE_SEPARATOR);
		}

		header.append( super.fileHeader(columns)   );

		String sep = "";
		for (Column col : columns) {
			char type = rdbType(col.type);

			header.append(sep).append(col.size).append(type);
			sep = getSeparator();
		}
		header.append( IoUtils.LINE_SEPARATOR );

		return header.toString();
	}



	protected char rdbType(int type) {
		char rdb = 's';

		switch(type) {

		case java.sql.Types.BIGINT:
		case java.sql.Types.BIT:
		case java.sql.Types.DECIMAL:
		case java.sql.Types.DOUBLE:
		case java.sql.Types.FLOAT:
		case java.sql.Types.INTEGER:
		case java.sql.Types.NUMERIC:
		case java.sql.Types.SMALLINT:
		case java.sql.Types.TINYINT:
			rdb = 'n'; break;

		case java.sql.Types.DATE:
		case java.sql.Types.TIME:
		case java.sql.Types.TIMESTAMP:
			rdb = 'd'; break;

		default:
			rdb = 's';

		}

		return rdb;
	}
}
