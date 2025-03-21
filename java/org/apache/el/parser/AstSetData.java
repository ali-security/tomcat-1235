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
/* Generated By:JJTree: Do not edit this line. AstSetData.java Version 4.3 */
package org.apache.el.parser;

import java.util.HashSet;
import java.util.Set;

import jakarta.el.ELException;

import org.apache.el.lang.EvaluationContext;

public class AstSetData extends SimpleNode {

    public AstSetData(int id) {
        super(id);
    }


    @Override
    public Object getValue(EvaluationContext ctx) throws ELException {
        Set<Object> result = new HashSet<>();

        if (children != null) {
            for (Node child : children) {
                result.add(child.getValue(ctx));
            }
        }

        return result;
    }


    @Override
    public Class<?> getType(EvaluationContext ctx) throws ELException {
        return Set.class;
    }
}
/* JavaCC - OriginalChecksum=e1dc4e2011eee313491decfa9e0152fe (do not edit this line) */
