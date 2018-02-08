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
package ca.uqac.lif.cep.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pushable;
import ca.uqac.lif.cep.functions.ApplyFunction;
import ca.uqac.lif.cep.tmf.SinkLast;
import ca.uqac.lif.xml.XPathExpression;
import ca.uqac.lif.xml.XPathExpression.XPathParseException;
import ca.uqac.lif.xml.XmlElement;
import ca.uqac.lif.xml.XmlElement.XmlParseException;

/**
 * Unit tests for the XML processors
 * @author Sylvain Hallé
 */
public class XmlTest
{

	@Test
	public void testSingle1() 
	{
		ApplyFunction feeder = new ApplyFunction(ParseXml.instance);
		Pushable in = feeder.getPushableInput(0);
		assertNotNull(in);
		SinkLast sink = new SinkLast(1);
		Connector.connect(feeder, sink);
		in.push("<a>123</a>");
		Object[] os = sink.getLast();
		assertNotNull(os);
		assertEquals(1, os.length);
		assertTrue(os[0] instanceof XmlElement);
	}
	
	@Test
	public void testSingle2() 
	{
		ApplyFunction feeder = new ApplyFunction(ParseXml.instance);
		Pushable in = feeder.getPushableInput(0);
		assertNotNull(in);
		SinkLast sink = new SinkLast(1);
		Connector.connect(feeder, sink);
		in.push("<a>123</a><b></b>");
		Object[] os = sink.getLast();
		assertNull(os);
	}
	
	@Test
	public void testXPath1() throws XPathParseException, XmlParseException
	{
		XPathEvaluator xpath = new XPathEvaluator(XPathExpression.parse("a/b/text()"));
		Pushable in = xpath.getPushableInput(0);
		assertNotNull(in);
		SinkLast sink = new SinkLast(1);
		Connector.connect(xpath, sink);
		in.push(XmlElement.parse("<a><b>1</b><b>2</b></a>"));
		Object[] os = sink.getLast();
		assertNotNull(os);
		assertTrue(os[0] instanceof Collection<?>);
	}

}
