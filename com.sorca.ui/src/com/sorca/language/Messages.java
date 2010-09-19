package com.sorca.language;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.sorca.language.messages"; //$NON-NLS-1$
	public static String Add2ToIndex;
	public static String AutoUpdate;
	public static String ByteCount;
	public static String Encoding;
	public static String EOL;
	public static String fileSelectionFilterALL;
	public static String fileSelectionFilterCBLTXT;
	public static String fileSelectionNameAllFiles;
	public static String fileSelectionNameCBLTXT;
	public static String GroupActions;
	public static String GroupExport;
	public static String GroupOptions;
	public static String GroupSpecial;
	public static String HandleRedefines;
	public static String Import;
	public static String IndexSpaces;
	public static String LabelInputArea;
	public static String LabelOutputArea;
	public static String LabelSingleArea;
	public static String OpenFile;
	public static String OpenFolder;
	public static String pasteText;
	public static String PicSpaces;
	public static String PrintErrors;
	public static String PrintFiller;
	public static String PrintFillerHint;
	public static String PrintHeader;
	public static String PrintHeaderHint;
	public static String TableField;
	public static String TableIndex;
	public static String TableOccurs;
	public static String TablePicType;
	public static String TablePicValue;
	public static String TableRedefines;
	public static String TableTotArea;
	public static String TableTotLevel;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
