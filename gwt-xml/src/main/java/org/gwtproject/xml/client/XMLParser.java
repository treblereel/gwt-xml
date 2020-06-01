/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.xml.client;

import org.gwtproject.xml.client.impl.XMLParserImpl;

/** This class represents the client interface to XML parsing. */
public class XMLParser {

  private static final XMLParserImpl impl = XMLParserImpl.getInstance();

  /** Not instantiable. */
  private XMLParser() {}

  /**
   * This method creates a new document, to be manipulated by the DOM API.
   *
   * @return the newly created document
   */
  public static Document createDocument() {
    return impl.createDocument();
  }

  /**
   * This method parses a new document from the supplied string, throwing a <code>DOMParseException
   * </code> if the parse fails.
   *
   * @param contents the String to be parsed into a <code>Document</code>
   * @return the newly created <code>Document</code>
   */
  public static Document parse(String contents) {
    return impl.parse(contents);
  }

  /**
   * This method removes all <code>Text</code> nodes which are made up of only white space.
   *
   * @param n the node which is to have all of its whitespace descendents removed.
   */
  public static void removeWhitespace(Node n) {
    impl.removeWhitespaceInner(n, null);
  }

  /**
   * This method determines whether the browser supports {@link CDATASection} as distinct entities
   * from <code>Text</code> nodes.
   *
   * @return true if the browser supports {@link CDATASection}, otherwise <code>false</code>.
   * @deprecated Always returns true
   */
  @Deprecated
  public static boolean supportsCDATASection() {
    return true;
  }
}
