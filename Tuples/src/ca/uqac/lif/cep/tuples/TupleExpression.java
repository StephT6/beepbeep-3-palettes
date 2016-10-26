package ca.uqac.lif.cep.tuples;

import java.util.Stack;

import ca.uqac.lif.cep.Processor;

/**
 * Association between an input trace (producing tuples) and a name.
 * This corresponds to the "something [AS somename]" portion of the
 * <code>FROM</code> clause in an SQL expression.
 */
class TupleExpression
{
	/**
	 * The input processor producing the tuples
	 */
	protected Processor m_processor;
	
	/**
	 * The name to be associated with
	 */
	protected String m_name;
	
	/**
	 * Creates a new TupleExpression
	 * @param p The input processor producing the tuples
	 * @param n The name to be associated with
	 */
	public TupleExpression(Processor p, String n)
	{
		super();
		m_processor = p;
		m_name = n;
	}
	
	public static void build(Stack<Object> stack)
	{
		// Do nothing
	}
	
	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		out.append("(").append(m_processor).append(")");
		if (m_name == null)
		{
			out.append(" AS ").append(m_name);
		}
		return out.toString();
	}
}