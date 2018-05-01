/*
Copyright (C) 2018 Adrian D. Finlay. All rights reserved.

Licensed under the MIT License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://opensource.org/licenses/MIT

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER INCLUDING AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
==============================================================================
**/

package src.me.adriandavid.jdk11eg;

import java.lang.reflect.Method;
import static java.lang.System.out;

public class Test {
	public static void main (String ... args) throws Exception {
		Double[] operands = new Double[2];
		try { 
			operands[0] = Double.parseDouble(args[0]);
			operands[1] = Double.parseDouble(args[1]);
		} 
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e) { 
			if (e instanceof ArrayIndexOutOfBoundsException )
				out.println("You have not specified enough operands.");
			else
				out.println("Your input is malformed.");
		 	return; 
		}

		/* Legal Implicitly Typed Syntax with Java 11 as of EA Build +4 (04/13/2017) */
		ITest divide = (@ATest var x, final var y) ->  x / y;
		out.println("Lambda Division:\t" + divide.doMath(operands[0], operands[1]) );
		
		/* Perform reflection on Annotated Lambda Expression Argument */
		Method meth = divide.getClass().getMethod("doMath", double.class, double.class);
		try {
			var sl = (java.lang.invoke.SerializedLambda)meth.invoke(divide);
		} catch (Exception e) { out.println("Attempt to perform reflection on SerializedLambda failed."); }

		/* Implicitly Typed Syntax: The old way */
		ITest multiply = (x,y) ->  x * y;
		out.println("Lambda Multiplication:\t" + multiply.doMath(operands[0], operands[1]) );

		/* Explicitly Typed Syntax: The most strongly typed way */
		ITest exp = (double x, double y) ->  Math.pow(x,y);
		out.println("Lambda Exponentiation:\t" + exp.doMath(operands[0], operands[1]) + "\n" );

		/* Semi-Explicit/Semi-Implicit Mix => Illegal */
		// Itest subtract = (var x, double y) -> x-y;

		/* Mixing Implicit Styles => Illegal */
		// Itest subtract = (var x, y) -> x-y;

		/* Modifiers on Old-Style implicit paramaters => Illegal */
		// ITest divide = (@ATest x, final y) ->  x / y;

		/* Omission of Parentheses => Illegal */
		// ITest2 upper = var x -> { return x.toUpperCase(); };
	}
}
