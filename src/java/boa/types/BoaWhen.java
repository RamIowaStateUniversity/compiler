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
package boa.types;

/**
 * A {@link BoaType} that represents a cfgvisitor.
 * 
 * @author rdyer
 */
public class BoaWhen extends BoaType {
	/**
	 * Construct a {@link BoaCFGVisitor}.
	 */
	public BoaWhen() {
	}

	/** {@inheritDoc} */
	@Override
	public boolean assigns(final BoaType that) {
		if (!(that instanceof BoaWhen))
			return false;

		return true;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "when";
	}

	/** {@inheritDoc} */
	@Override
	public String toJavaType() {
		return "boa.runtime.BoaAbstractWhen";
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		return true;
	}
}
