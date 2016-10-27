/*
 * Copyright 2014, Hridesh Rajan, Robert Dyer, 
 *                 and Iowa State University of Science and Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package boa.runtime;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import boa.functions.BoaAstIntrinsics;

import boa.types.Ast.*;
import boa.types.Ast.Expression.*;
import boa.types.Control.*;
import boa.types.Graph.*;
import boa.types.Code.CodeRepository;
import boa.types.Code.Revision;
import boa.types.Diff.ChangedFile;
import boa.types.Shared.Person;
import boa.types.Toplevel.Project;

/**
 * Boa abstract AST visitor.
 * 
 * The <code>traverse()</code> methods first call <code>preTraverse()</code> for the node.
 * If <code>preTraverse()</code> returns <code>true</code>, then each of that node's children are visited and then <code>postTraverse()</code> is called.
 * 
 * By default, all <code>preTraverse()</code> methods call {@link #defaultPreTraverse()} and return <code>true</code>.
 * By default, all <code>postTraverse()</code> methods call {@link #defaultPostTraverse()}.
 * 
 * @author rdyer
 */
public abstract class BoaAbstractFixP {
	
	public boolean invoke(Object curr, Object prev) throws Exception{
		return true;
	}

	/**
	 * Initializes any visitor-specific data before starting a visit.
	 * 
	 * @return itself, to allow method chaining
	 */
	public BoaAbstractFixP initialize() {
		return this;
	}
}
