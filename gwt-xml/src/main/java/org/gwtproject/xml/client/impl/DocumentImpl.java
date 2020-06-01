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

import org.gwtproject.xml.client.CDATASection;
import org.gwtproject.xml.client.Comment;
import org.gwtproject.xml.client.DOMException;
import org.gwtproject.xml.client.Document;
import org.gwtproject.xml.client.DocumentFragment;
import org.gwtproject.xml.client.Element;
import org.gwtproject.xml.client.Node;
import org.gwtproject.xml.client.NodeList;
import org.gwtproject.xml.client.ProcessingInstruction;
import org.gwtproject.xml.client.Text;

/** This class wraps the native Document object. */
class DocumentImpl extends NodeImpl implements Document {

  protected final elemental2.dom.Document document;

  protected DocumentImpl(elemental2.dom.Document document) {
    super(document);
    this.document = document;
  }

  /**
   * This function delegates to the native method <code>createCDATASection</code> in XMLParserImpl.
   */
  public CDATASection createCDATASection(String data) {
    try {
      return new CDATASectionImpl(document.createCDATASection(data));
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_CHARACTER_ERR, e, document);
    }
  }

  /** This function delegates to the native method <code>createComment</code> in XMLParserImpl. */
  public Comment createComment(String data) {
    try {
      return new CommentImpl(document.createComment(data));
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_CHARACTER_ERR, e, document);
    }
  }

  /**
   * This function delegates to the native method <code>createDocumentFragment</code> in
   * XMLParserImpl.
   */
  public DocumentFragment createDocumentFragment() {
    try {
      return new DocumentFragmentImpl(document.createDocumentFragment());
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_CHARACTER_ERR, e, document);
    }
  }

  /** This function delegates to the native method <code>createElement</code> in XMLParserImpl. */
  public Element createElement(String tagName) {
    try {
      return new ElementImpl(document.createElement(tagName));
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_CHARACTER_ERR, e, document);
    }
  }

  /**
   * This function delegates to the native method <code>createProcessingInstruction</code> in
   * XMLParserImpl.
   */
  public ProcessingInstruction createProcessingInstruction(String target, String data) {
    try {
      return new ProcessingInstructionImpl(document.createProcessingInstruction(target, data));
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_CHARACTER_ERR, e, document);
    }
  }

  /** This function delegates to the native method <code>createTextNode</code> in XMLParserImpl. */
  public Text createTextNode(String data) {
    try {
      return new TextImpl(document.createTextNode(data));
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_CHARACTER_ERR, e, document);
    }
  }

  /**
   * This function delegates to the native method <code>getDocumentElement</code> in XMLParserImpl.
   */
  public Element getDocumentElement() {
    return new ElementImpl(document.documentElement);
  }

  /** This function delegates to the native method <code>getElementById</code> in XMLParserImpl. */
  public Element getElementById(String elementId) {
    return new ElementImpl(document.getElementById(elementId));
  }

  /**
   * This function delegates to the native method <code>getElementsByTagName</code> in
   * XMLParserImpl.
   */
  public NodeList getElementsByTagName(String tagName) {
    return new NodeListImpl(document.getElementsByTagName(tagName));
  }

  /** This function delegates to the native method <code>importNode</code> in XMLParserImpl. */
  public Node importNode(Node importedNode, boolean deep) {
    Node actualNode = (Node) importedNode;
    try {
      return XMLParserImpl.importNode(this, actualNode, deep);
    } catch (Exception e) {
      throw new DOMNodeException(DOMException.INVALID_STATE_ERR, e, document);
    }
  }
}
