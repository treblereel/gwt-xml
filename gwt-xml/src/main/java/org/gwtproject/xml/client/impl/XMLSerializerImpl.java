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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.gwtproject.xml.client.Node;

/** @author Dmitrii Tikhomirov Created by treblereel 6/1/20 */
@JsType(isNative = true, name = "XMLSerializer", namespace = JsPackage.GLOBAL)
public class XMLSerializerImpl {

  @JsOverlay
  public final String _serializeToString(Node node) {
    return serializeToString(((NodeImpl) node).node);
  }

  @JsOverlay
  public final String _serializeToString(elemental2.dom.Node node) {
    return serializeToString(node);
  }

  private native String serializeToString(elemental2.dom.Node node);
}
