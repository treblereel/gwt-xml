/*
 * Copyright 2007 Google Inc.
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

import org.gwtproject.xml.client.Node;
import org.gwtproject.xml.client.NodeList;

/**
 * This class implements the NodeList interface using the underlying JavaScriptObject's
 * implementation.
 */
class NodeListImpl implements NodeList {

  private final elemental2.dom.NodeList domList;

  protected NodeListImpl(elemental2.dom.NodeList o) {
    this.domList = o;
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < getLength(); i++) {
      b.append(item(i).toString());
    }
    return b.toString();
  }

  @Override
  public int getLength() {
    return domList.length;
  }

  /**
   * This method gets the index item.
   *
   * @param index - the index to be retrieved
   * @return the item at this index
   * @see org.gwtproject.xml.client.NodeList#item(int)
   */
  @Override
  public Node item(int index) {
    if (index >= domList.length) {
      return null;
    }
    return NodeImpl.build((elemental2.dom.Node) domList.item(index));
  }
}
