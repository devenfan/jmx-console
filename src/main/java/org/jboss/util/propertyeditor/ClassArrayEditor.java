/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.util.propertyeditor;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.beans.PropertyEditorSupport;

/** A property editor for Class[].
 *
 * @version $Revision: 1527 $
 * @author Scott.Stark@jboss.org
 */
public class ClassArrayEditor extends PropertyEditorSupport
{
   /** Build a Class[] from a comma/whitespace seperated list of classes
    * @param text - the class name list
    */
   public void setAsText(final String text) throws IllegalArgumentException
   {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      StringTokenizer tokenizer = new StringTokenizer(text, ", \t\r\n");
      ArrayList classes = new ArrayList();
      while( tokenizer.hasMoreTokens() == true )
      {
         String name = tokenizer.nextToken();
         try
         {
            Class c = loader.loadClass(name);
            classes.add(c);
         }
         catch(ClassNotFoundException e)
         {
            throw new IllegalArgumentException("Failed to find class: "+name);
         }
      }

      Class[] theValue = new Class[classes.size()];
      classes.toArray(theValue);
      setValue(theValue);
   }

   /**
    * @return a comma seperated string of the class array
    */
   public String getAsText()
   {
      Class[] theValue = (Class[]) getValue();
      StringBuffer text = new StringBuffer();
      int length = theValue == null ? 0 : theValue.length;
      for(int n = 0; n < length; n ++)
      {
         text.append(theValue[n].getName());
         text.append(',');
      }
      // Remove the trailing ','
      text.setLength(text.length()-1);
      return text.toString();
   }
}
