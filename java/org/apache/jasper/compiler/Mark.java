/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jasper.compiler;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.jasper.JspCompilationContext;

/**
 * Mark represents a point in the JSP input.
 *
 * @author Anil K. Vijendran
 */
final class Mark {

    // position within current stream
    int cursor, line, col;

    // directory of file for current stream
    String baseDir;

    // current stream
    char[] stream = null;

    // fileid of current stream
    private int fileId;

    // name of the current file
    private String fileName;

    // reader that owns this mark (so we can look up fileid's)
    private JspReader reader;

    private JspCompilationContext ctxt;

    /**
     * Constructor
     *
     * @param reader JspReader this mark belongs to
     * @param inStream current stream for this mark
     * @param fileId id of requested jsp file
     * @param name JSP file name
     * @param inBaseDir base directory of requested jsp file
     */
    Mark(JspReader reader, char[] inStream, int fileId, String name,
         String inBaseDir) {

        this.reader = reader;
        this.ctxt = reader.getJspCompilationContext();
        this.stream = inStream;
        this.cursor = 0;
        this.line = 1;
        this.col = 1;
        this.fileId = fileId;
        this.fileName = name;
        this.baseDir = inBaseDir;
    }


    /**
     * Constructor
     */
    Mark(Mark other) {
       init(other, false);
    }

    void update(int cursor, int line, int col) {
        this.cursor = cursor;
        this.line = line;
        this.col = col;
    }

    void init(Mark other, boolean singleFile) {
        this.cursor = other.cursor;
        this.line = other.line;
        this.col = other.col;

        if (!singleFile) {
            this.reader = other.reader;
            this.ctxt = other.ctxt;
            this.stream = other.stream;
            this.fileId = other.fileId;
            this.fileName = other.fileName;
            this.baseDir = other.baseDir;
        }
    }


    /**
     * Constructor
     */
    Mark(JspCompilationContext ctxt, String filename, int line, int col) {

        this.reader = null;
        this.ctxt = ctxt;
        this.stream = null;
        this.cursor = 0;
        this.line = line;
        this.col = col;
        this.fileId = -1;
        this.fileName = filename;
        this.baseDir = "le-basedir";
    }


    public int getLineNumber() {
        return line;
    }

    public int getColumnNumber() {
        return col;
    }

    @Override
    public String toString() {
        return getFile()+"("+line+","+col+")";
    }

    public String getFile() {
        return this.fileName;
    }

    /**
     * Gets the URL of the resource with which this Mark is associated
     *
     * @return URL of the resource with which this Mark is associated
     *
     * @exception MalformedURLException if the resource pathname is incorrect
     */
    public URL getURL() throws MalformedURLException {
        return ctxt.getResource(getFile());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Mark) {
            Mark m = (Mark) other;
            return this.reader == m.reader && this.fileId == m.fileId
                && this.cursor == m.cursor && this.line == m.line
                && this.col == m.col;
        }
        return false;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + cursor;
        result = prime * result + fileId;
        result = prime * result + line;
        result = prime * result + ((reader == null) ? 0 : reader.hashCode());
        return result;
    }
}
