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

import java.io.File;
import java.io.IOException;


/**
 * A property editor for {@link java.io.File}.
 *
 * @version <tt>$Revision: 1455 $</tt>
 * @author  <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
public class FileEditor extends TextPropertyEditorSupport
{
   /**
    * Returns a URL for the input object converted to a string.
    *
    * @return a URL object
    *
    * @throws NestedRuntimeException   An IOException occured.
    */
   public Object getValue()
   {
      try
      {
         return new File(getAsText()).getCanonicalFile();
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }
}
