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

import elemental2.dom.CDATASection;
import elemental2.dom.Comment;
import elemental2.dom.DocumentFragment;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.ProcessingInstruction;
import elemental2.dom.Text;
import java.util.Objects;
import org.gwtproject.xml.client.DOMException;
import org.gwtproject.xml.client.Document;
import org.gwtproject.xml.client.NamedNodeMap;
import org.gwtproject.xml.client.Node;
import org.gwtproject.xml.client.NodeList;

/** This class wraps the native Node object. */
class NodeImpl implements Node {

  /**
   * This method creates a new node of the correct type.
   *
   * @param node - the supplied DOM JavaScript object
   * @return a Node object that corresponds to the DOM object
   */
  static Node build(elemental2.dom.Node node) {
    if (node == null) {
      return null;
    }

    DomGlobal.console.log("build " + node.nodeType + " " + node.nodeName + " " + node);

    switch (node.nodeType) {
      case Node.ATTRIBUTE_NODE:
        return new AttrImpl((elemental2.dom.Attr) node);
      case Node.CDATA_SECTION_NODE:
        return new CDATASectionImpl((CDATASection) node);
      case Node.COMMENT_NODE:
        return new CommentImpl((Comment) node);
      case Node.DOCUMENT_FRAGMENT_NODE:
        return new DocumentFragmentImpl((DocumentFragment) node);
      case Node.DOCUMENT_NODE:
        return new DocumentImpl((elemental2.dom.Document) node);
      case Node.ELEMENT_NODE:
        return new ElementImpl((Element) node);
      case Node.PROCESSING_INSTRUCTION_NODE:
        return new ProcessingInstructionImpl((ProcessingInstruction) node);
      case Node.TEXT_NODE:
        return new TextImpl((Text) node);
      default:
        return new NodeImpl(node);
    }
  }

  final elemental2.dom.Node node;

  /**
   * creates a new NodeImpl from the supplied JavaScriptObject.
   *
   * @param jso - the DOM node JavaScriptObject
   */
  protected NodeImpl(elemental2.dom.Node jso) {
    this.node = jso;
  }

  /** This function delegates to the native method <code>appendChild</code> in XMLParserImpl. */
  @Override
  public Node appendChild(Node newChild) {
    NodeImpl c = (NodeImpl) newChild;
    try {
      final elemental2.dom.Node appendChildResults = node.appendChild(c.node);
      return NodeImpl.build(appendChildResults);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, node);
    }
  }

  /** This function delegates to the native method <code>cloneNode</code> in XMLParserImpl. */
  @Override
  public Node cloneNode(boolean deep) {
    return NodeImpl.build(node.cloneNode(deep));
  }

  @Override
  public NamedNodeMap getAttributes() {
    return new NamedNodeMapImpl(node.attributes);
  }

  @Override
  public NodeList getChildNodes() {
    return new NodeListImpl(node.childNodes);
  }

  @Override
  public Node getFirstChild() {
    return getChildNodes().item(0);
  }

  @Override
  public Node getLastChild() {
    return getChildNodes().item(getChildNodes().getLength() - 1);
  }

  /** This function delegates to the native method <code>getNamespaceURI</code> in XMLParserImpl. */
  @Override
  public String getNamespaceURI() {
    return node.namespaceURI;
  }

  @Override
  public Node getNextSibling() {
    return NodeImpl.build(node.nextSibling);
  }

  @Override
  public String getNodeName() {
    return node.nodeName;
  }

  @Override
  public short getNodeType() {
    return (short) node.nodeType;
  }

  @Override
  public String getNodeValue() {
    return node.nodeValue;
  }

  @Override
  public Document getOwnerDocument() {
    return (Document) NodeImpl.build(node.ownerDocument);
  }

  @Override
  public Node getParentNode() {
    return NodeImpl.build(node.parentNode);
  }

  /** This function delegates to the native method <code>getPrefix</code> in XMLParserImpl. */
  @Override
  public String getPrefix() {
    return XMLParserImpl.getPrefix(node);
  }

  @Override
  public Node getPreviousSibling() {
    return NodeImpl.build(node.previousSibling);
  }

  /** This function delegates to the native method <code>hasAttributes</code> in XMLParserImpl. */
  @Override
  public boolean hasAttributes() {
    return node.attributes.length > 0;
  }

  /** This function delegates to the native method <code>hasChildNodes</code> in XMLParserImpl. */
  @Override
  public boolean hasChildNodes() {
    return node.hasChildNodes();
  }

  /** This function delegates to the native method <code>insertBefore</code> in XMLParserImpl. */
  @Override
  public Node insertBefore(Node newChild, Node refChild) {
    try {
      final elemental2.dom.Node newChildJs = ((NodeImpl) newChild).node;
      final elemental2.dom.Node refChildJs;
      if (refChild != null) {
        refChildJs = ((NodeImpl) refChild).node;
      } else {
        refChildJs = null;
      }
      elemental2.dom.Node insertBeforeResults = node.insertBefore(newChildJs, refChildJs);
      return NodeImpl.build(insertBeforeResults);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, node);
    }
  }

  /** This function delegates to the native method <code>normalize</code> in XMLParserImpl. */
  @Override
  public void normalize() {
    node.normalize();
  }

  /** This function delegates to the native method <code>removeChild</code> in XMLParserImpl. */
  @Override
  public Node removeChild(Node oldChild) {
    try {
      elemental2.dom.Node oldChildJs = ((NodeImpl) oldChild).node;
      elemental2.dom.Node removeChildResults = node.removeChild(oldChildJs);
      return NodeImpl.build(removeChildResults);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, node);
    }
  }

  /** This function delegates to the native method <code>replaceChild</code> in XMLParserImpl. */
  @Override
  public Node replaceChild(Node newChild, Node oldChild) {
    try {
      final elemental2.dom.Node newChildJs = ((NodeImpl) newChild).node;
      final elemental2.dom.Node oldChildJs = ((NodeImpl) oldChild).node;
      final elemental2.dom.Node replaceChildResults = node.replaceChild(newChildJs, oldChildJs);
      return NodeImpl.build(replaceChildResults);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, node);
    }
  }

  /** This function delegates to the native method <code>setNodeValue</code> in XMLParserImpl. */
  @Override
  public void setNodeValue(String nodeValue) {
    try {
      node.nodeValue = nodeValue;
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_MODIFICATION_ERR, e, node);
    }
  }

  @Override
  public String toString() {
    return XMLParserImpl.getInstance().toStringImpl(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NodeImpl)) {
      return false;
    }
    NodeImpl node1 = (NodeImpl) o;
    return Objects.equals(node, node1.node);
  }

  @Override
  public int hashCode() {
    return Objects.hash(node);
  }
}
