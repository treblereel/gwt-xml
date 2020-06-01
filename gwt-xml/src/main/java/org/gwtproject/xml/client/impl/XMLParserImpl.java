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

import elemental2.dom.Document;
import elemental2.dom.DocumentType;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.Node;
import java.util.ArrayList;
import java.util.List;
import jsinterop.base.Js;
import org.gwtproject.xml.client.DOMParser;

/** Native implementation associated with {@link org.gwtproject.xml.client.XMLParser}. */
public abstract class XMLParserImpl {

  private static XMLParserImpl impl;

  /** Not globally instantable. */
  XMLParserImpl() {}

  public static XMLParserImpl getInstance() {
    if (impl == null) {
      impl = createImpl();
    }

    return impl;
  }

  private static XMLParserImpl createImpl() {
    return new XMLParserImplStandard();
  }

  static org.gwtproject.xml.client.Element getElementById(Document document, String id) {
    return impl.getElementByIdImpl(document, id);
  }

  protected abstract org.gwtproject.xml.client.Element getElementByIdImpl(
      Document document, String id);

  static org.gwtproject.xml.client.NodeList getElementsByTagName(Node o, String tagName) {
    return impl.getElementsByTagNameImpl(o, tagName);
  }

  protected abstract org.gwtproject.xml.client.NodeList getElementsByTagNameImpl(
      Node node, String tagName);

  static String getPrefix(Node node) {
    return impl.getPrefixImpl(node);
  }

  protected abstract String getPrefixImpl(Node node);

  static org.gwtproject.xml.client.Node importNode(
      org.gwtproject.xml.client.Document document,
      org.gwtproject.xml.client.Node importedNode,
      boolean deep) {
    return impl.importNodeImpl(
        ((DocumentImpl) document).document, ((NodeImpl) importedNode).node, deep);
  }

  protected abstract org.gwtproject.xml.client.Node importNodeImpl(
      Document document, Node importedNode, boolean deep);

  public final org.gwtproject.xml.client.Document createDocument() {
    return new DocumentImpl(DomGlobal.document.implementation.createDocument("", "", null));
  }

  public final org.gwtproject.xml.client.Document createDocument(
      String namespace, String publicId, DocumentType docType) {
    return new DocumentImpl(
        DomGlobal.document.implementation.createDocument(namespace, publicId, docType));
  }

  public final org.gwtproject.xml.client.Document parse(String contents) {
    Document doc = new DOMParser().parseFromString(contents, "text/xml");
    if (doc.getElementsByTagNameNS("*", "parsererror").length > 0) {
      throw new DOMParseException(contents);
    }
    return new DocumentImpl(doc);
  }

  protected abstract org.gwtproject.xml.client.Document createDocumentImpl();

  protected abstract org.gwtproject.xml.client.Document parseImpl(String contents);

  abstract String toStringImpl(org.gwtproject.xml.client.Node node);

  /*
   * The inner recursive method for removeWhitespace
   */
  public void removeWhitespaceInner(
      org.gwtproject.xml.client.Node n, org.gwtproject.xml.client.Node parent) {
    // This n is removed from the parent if n is a whitespace node
    if (parent != null
        && n instanceof org.gwtproject.xml.client.Text
        && (!(n instanceof org.gwtproject.xml.client.CDATASection))) {
      org.gwtproject.xml.client.impl.TextImpl t = (org.gwtproject.xml.client.impl.TextImpl) n;
      if (t.text.data.matches("[ \t\n]*")) {
        parent.removeChild(t);
      }
    }
    if (n.hasChildNodes()) {
      int length = n.getChildNodes().getLength();
      List<org.gwtproject.xml.client.Node> toBeProcessed = new ArrayList<>();
      // We collect all the nodes to iterate as the child nodes will change
      // upon removal
      for (int i = 0; i < length; i++) {
        toBeProcessed.add(n.getChildNodes().item(i));
      }
      // This changes the child nodes, but the iterator of nodes never changes
      // meaning that this is safe
      for (org.gwtproject.xml.client.Node childNode : toBeProcessed) {
        removeWhitespaceInner(childNode, n);
      }
    }
  }

  /**
   * This class implements the methods for standard browsers that use the DOMParser model of XML
   * parsing.
   */
  private static class XMLParserImplStandard extends XMLParserImpl {

    protected final DOMParser domParser = new DOMParser();

    @Override
    protected org.gwtproject.xml.client.Element getElementByIdImpl(Document document, String id) {
      return new ElementImpl(document.getElementById(id));
    }

    @Override
    protected org.gwtproject.xml.client.NodeList getElementsByTagNameImpl(Node o, String tagName) {
      return new NodeListImpl(Js.<Element>uncheckedCast(o).getElementsByTagNameNS("*", tagName));
    }

    @Override
    protected String getPrefixImpl(Node node) {
      String fullName = node.nodeName;
      if (fullName != null && fullName.indexOf(":") != -1) {
        return fullName.split(":", 2)[0];
      }
      return null;
    }

    @Override
    protected org.gwtproject.xml.client.Node importNodeImpl(
        Document document, Node importedNode, boolean deep) {
      return new NodeImpl(document.importNode(importedNode, deep));
    }

    @Override
    protected org.gwtproject.xml.client.Document createDocumentImpl() {
      throw new UnsupportedOperationException();
    }

    @Override
    protected org.gwtproject.xml.client.Document parseImpl(String contents) {
      Document result = domParser.parseFromString(contents, "text/xml");

      Element rootTag = result.documentElement;

      if ("parsererror".equals(rootTag.tagName)
          && "http://www.mozilla.org/newlayout/xml/parsererror.xml".equals(rootTag.namespaceURI)) {
        throw new RuntimeException(rootTag.firstChild.toString());
      }

      return new DocumentImpl(result);
    }

    @Override
    protected String toStringImpl(org.gwtproject.xml.client.Node node) {
      return new XMLSerializerImpl()._serializeToString(node);
    }
  }
}
