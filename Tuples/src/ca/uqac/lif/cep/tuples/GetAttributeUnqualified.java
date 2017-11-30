/*
    BeepBeep, an event stream processor
    Copyright (C) 2008-2017 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.cep.tuples;

import java.util.ArrayDeque;

class GetAttributeUnqualified extends GetAttribute
{
	public GetAttributeUnqualified(String attribute_name) 
	{
		super(attribute_name);
	}

	public static void build(ArrayDeque<Object> stack)
	{
		String n = (String) stack.pop();
		GetAttribute ga = new GetAttribute(n);
		stack.push(ga);
	}
}
