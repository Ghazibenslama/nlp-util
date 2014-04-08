package com.bbn.bue.common.converters;

import java.io.File;

import com.bbn.bue.common.os.OSDetector;

import static com.google.common.base.Preconditions.*;

public class StringToOSFile implements StringConverter<File> {
    public StringToOSFile() { }

    public Class<File> getValueClass() { return File.class; }

    @Override
    public File decode(final String s) {
    	String path = s;
    	if (OSDetector.isWindows()) {
    		path = path.replace("/nfs/","\\\\").replace("/", "\\");
    	} else {
    		path = path.replace("\\\\", "/nfs/").replace("\\", "/");
    	}
    	return new File(checkNotNull(path)); 
    }
}
