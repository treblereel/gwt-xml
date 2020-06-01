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
package org.gwtproject.xml.client.impl;

import org.gwtproject.xml.client.CharacterData;
import org.gwtproject.xml.client.DOMException;

/** This class implements the CharacterData interface. */
abstract class CharacterDataImpl extends NodeImpl implements CharacterData {

  private elemental2.dom.CharacterData charNode;

  protected CharacterDataImpl(elemental2.dom.CharacterData o) {
    super(o);
    this.charNode = o;
  }

  /** This function delegates to the native method <code>appendData</code> in XMLParserImpl. */
  @Override
  public void appendData(String arg) {
    try {
      charNode.appendData(arg);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, charNode);
    }
  }

  /** This function delegates to the native method <code>deleteData</code> in XMLParserImpl. */
  @Override
  public void deleteData(int offset, int count) {
    try {
      charNode.deleteData(offset, count);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, charNode);
    }
  }

  /** This function delegates to the native method <code>getData</code> in XMLParserImpl. */
  @Override
  public String getData() {
    return charNode.data;
  }

  /** This function delegates to the native method <code>getLength</code> in XMLParserImpl. */
  @Override
  public int getLength() {
    return charNode.length;
  }

  /** This function delegates to the native method <code>insertData</code> in XMLParserImpl. */
  @Override
  public void insertData(int offset, String arg) {
    try {
      charNode.insertData(offset, arg);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, charNode);
    }
  }

  /** This function delegates to the native method <code>replaceData</code> in XMLParserImpl. */
  @Override
  public void replaceData(int offset, int count, String arg) {
    try {
      charNode.replaceData(offset, count, arg);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, charNode);
    }
  }

  /** This function delegates to the native method <code>setData</code> in XMLParserImpl. */
  @Override
  public void setData(String data) {
    try {
      charNode.data = data;
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, charNode);
    }
  }

  /** This function delegates to the native method <code>substringData</code> in XMLParserImpl. */
  @Override
  public String substringData(int offset, int count) {
    try {
      return charNode.substringData(offset, count);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_ACCESS_ERR, e, charNode);
    }
  }
}
