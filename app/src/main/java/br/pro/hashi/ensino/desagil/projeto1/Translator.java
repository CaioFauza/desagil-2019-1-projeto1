package br.pro.hashi.ensino.desagil.projeto1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// Não é permitido mudar nada nessa classe
// exceto o recheio dos três métodos.

public class Translator {
    private final Node root;
    private final HashMap<Character, Node> map;


    // Você deve mudar o recheio deste construtor,
    // de acordo com os requisitos não-funcionais.
    public Translator() {
        map = new HashMap<>();
        root = new Node('*');
        map.put('*', root);

        // Linha1
        Node nodeE = new Node('e');
        nodeE.setParent(root);
        Node nodeT = new Node('t');
        nodeT.setParent(root);
        root.setLeft(nodeE);
        root.setRight(nodeT);
        map.put('e', nodeE);
        map.put('t', nodeT);

        // Linha 2
        Node nodeI = new Node('i');
        Node nodeA = new Node('a');
        nodeI.setParent(nodeE);
        nodeA.setParent(nodeE);
        nodeE.setLeft(nodeI);
        nodeE.setRight(nodeA);
        Node nodeN = new Node('n');
        Node nodeM = new Node('m');
        nodeN.setParent(nodeT);
        nodeM.setParent(nodeT);
        nodeT.setLeft(nodeN);
        nodeT.setRight(nodeM);
        map.put('i', nodeI);
        map.put('a', nodeA);
        map.put('n', nodeN);
        map.put('m', nodeM);


        // Linha 3
        Node nodeS = new Node('s');
        Node nodeU = new Node('u');
        nodeS.setParent(nodeI);
        nodeU.setParent(nodeI);
        nodeI.setLeft(nodeS);
        nodeI.setRight(nodeU);
        Node nodeR = new Node('r');
        Node nodeW = new Node('w');
        nodeR.setParent(nodeA);
        nodeW.setParent(nodeA);
        nodeA.setLeft(nodeR);
        nodeA.setRight(nodeW);
        Node nodeD = new Node('d');
        Node nodeK = new Node('k');
        nodeD.setParent(nodeN);
        nodeK.setParent(nodeN);
        nodeN.setLeft(nodeD);
        nodeN.setRight(nodeK);
        Node nodeG = new Node('g');
        Node nodeO = new Node('o');
        nodeG.setParent(nodeM);
        nodeO.setParent(nodeM);
        nodeM.setLeft(nodeG);
        nodeM.setRight(nodeO);
        map.put('s', nodeS);
        map.put('u', nodeU);
        map.put('r', nodeR);
        map.put('w', nodeW);
        map.put('d', nodeD);
        map.put('k', nodeK);
        map.put('g', nodeG);
        map.put('o', nodeO);

        // Linha 4
        Node nodeH = new Node('h');
        Node nodeV = new Node('v');
        nodeH.setParent(nodeS);
        nodeV.setParent(nodeS);
        nodeS.setLeft(nodeH);
        nodeS.setRight(nodeV);
        Node nodeF = new Node('f');
        Node node4blank = new Node('!');
        nodeF.setParent(nodeU);
        node4blank.setParent(nodeU);
        nodeU.setLeft(nodeF);
        nodeU.setRight(node4blank);
        Node nodeL = new Node('l');
        Node node4blank1 = new Node('!');
        nodeL.setParent(nodeR);
        node4blank1.setParent(nodeR);
        nodeR.setLeft(nodeL);
        nodeR.setRight(node4blank1);
        Node nodeP = new Node('p');
        Node nodeJ = new Node('j');
        nodeP.setParent(nodeW);
        nodeJ.setParent(nodeW);
        nodeW.setLeft(nodeP);
        nodeW.setRight(nodeJ);
        Node nodeB = new Node('b');
        Node nodeX =new Node('x');
        nodeB.setParent(nodeD);
        nodeX.setParent(nodeD);
        nodeD.setLeft(nodeB);
        nodeD.setRight(nodeX);
        Node nodeC = new Node('c');
        Node nodeY = new Node('y');
        nodeC.setParent(nodeK);
        nodeY.setParent(nodeK);
        nodeK.setLeft(nodeC);
        nodeK.setRight(nodeY);
        Node nodeZ = new Node('z');
        Node nodeQ = new Node('q');
        nodeZ.setParent(nodeG);
        nodeQ.setParent(nodeG);
        nodeG.setLeft(nodeZ);
        nodeG.setRight(nodeQ);
        Node node4blank2 = new Node('!');
        Node node4blank3 = new Node('!');
        node4blank2.setParent(nodeO);
        node4blank3.setParent(nodeO);
        nodeO.setLeft(node4blank2);
        nodeO.setRight(node4blank3);
        map.put('h', nodeH);
        map.put('v', nodeV);
        map.put('f', nodeF);
        map.put('l', nodeL);
        map.put('p', nodeP);
        map.put('j', nodeJ);
        map.put('b', nodeB);
        map.put('x', nodeX);
        map.put('c', nodeC);
        map.put('y', nodeY);
        map.put('z', nodeZ);
        map.put('q', nodeQ);


        // Linha 5
        Node node5 = new Node('5');
        Node node4 = new Node('4');
        node5.setParent(nodeH);
        node4.setParent(nodeH);
        nodeH.setLeft(node5);
        nodeH.setRight(node4);
        Node node5blank = new Node('!');
        Node node3 = new Node ('3');
        node5blank.setParent(nodeV);
        node3.setParent(nodeV);
        nodeV.setLeft(node5blank);
        nodeV.setRight(node3);
        Node node5blank1 = new Node('!');
        Node node5blank2 = new Node('!');
        node5blank1.setParent(nodeF);
        node5blank2.setParent(nodeF);
        nodeF.setLeft(node5blank1);
        nodeF.setRight(node5blank2);
        Node node5blank3 = new Node('!');
        Node node2 = new Node('2');
        node5blank3.setParent(node4blank);
        node2.setParent(node4blank);
        node4blank.setLeft(node5blank3);
        node4blank.setRight(node2);
        Node node5blank4 = new Node('!');
        Node node5blank5 = new Node('!');
        node5blank4.setParent(nodeL);
        node5blank5.setParent(nodeL);
        nodeL.setLeft(node5blank4);
        nodeL.setRight(node5blank5);
        Node nodeplus = new Node('+');
        Node node5blank6 = new Node('!');
        nodeplus.setParent(node4blank1);
        node5blank6.setParent(node4blank1);
        node4blank1.setLeft(nodeplus);
        node4blank1.setRight(node5blank6);
        Node node5blank7 = new Node('!');
        Node node5blank8 = new Node('!');
        node5blank7.setParent(nodeP);
        node5blank8.setParent(nodeP);
        nodeP.setLeft(node5blank7);
        nodeP.setRight(node5blank8);
        Node node5blank9 = new Node('!');
        Node node1 = new Node('1');
        node5blank9.setParent(nodeJ);
        node1.setParent(nodeJ);
        nodeJ.setLeft(node5blank9);
        nodeJ.setRight(node1);
        Node node6 = new Node('6');
        Node nodeequal = new Node('=');
        node6.setParent(nodeB);
        nodeequal.setParent(nodeB);
        nodeB.setLeft(node6);
        nodeB.setRight(nodeequal);
        Node nodebar = new Node('/');
        Node node5blank10 = new Node('!');
        nodebar.setParent(nodeX);
        node5blank10.setParent(nodeX);
        nodeX.setLeft(nodebar);
        nodeX.setRight(node5blank10);
        Node node5blank11 = new Node('!');
        Node node5blank12 = new Node('!');
        node5blank11.setParent(nodeC);
        node5blank12.setParent(nodeC);
        nodeC.setLeft(node5blank11);
        nodeC.setRight(node5blank12);
        Node node5blank13 = new Node('!');
        Node node5blank14 = new Node('!');
        node5blank13.setParent(nodeY);
        node5blank14.setParent(nodeY);
        nodeY.setLeft(node5blank13);
        nodeY.setRight(node5blank14);
        Node node7 = new Node('7');
        Node node5blank15 = new Node('!');
        node7.setParent(nodeZ);
        node5blank15.setParent(nodeZ);
        nodeZ.setLeft(node7);
        nodeZ.setRight(node5blank15);
        Node node5blank16 = new Node('!');
        Node node5blank17 = new Node('!');
        node5blank16.setParent(nodeQ);
        node5blank17.setParent(nodeQ);
        nodeQ.setLeft(node5blank16);
        nodeQ.setRight(node5blank17);
        Node node8 = new Node('8');
        Node node5blank18 = new Node('!');
        node8.setParent(node4blank2);
        node5blank18.setParent(node4blank2);
        node4blank2.setLeft(node8);
        node4blank2.setRight(node5blank18);
        Node node9 = new Node('9');
        Node node0 = new Node('0');
        node9.setParent(node4blank3);
        node0.setParent(node4blank3);
        node4blank3.setLeft(node9);
        node4blank3.setRight(node0);
        map.put('5', node5);
        map.put('4', node4);
        map.put('3', node3);
        map.put('2', node2);
        map.put('+', nodeplus);
        map.put('1', node1);
        map.put('6', node6);
        map.put('=', nodeequal);
        map.put('/', nodebar);
        map.put('7', node7);
        map.put('8', node8);
        map.put('9', node9);
        map.put('0', node0);

    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public char morseToChar(String code) {
        Node morseNode = root;
        char morseNodeValue;
        if (code.length() < 6) {
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '.') {
                    morseNode = morseNode.getLeft();
                } else {
                    morseNode = morseNode.getRight();
                }
            }
        }
        morseNodeValue = morseNode.getValue();
        return morseNodeValue;
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public String charToMorse(char c) {
        Node nodeChild = map.get(c);
        String morseTranslated = "";

        while(nodeChild.getValue() != '*'){
            Node nodeParent = nodeChild.getParent();
            if(nodeParent.getLeft() == nodeChild){

                morseTranslated += ".";
                nodeChild = nodeParent;


            } else {
                morseTranslated += "-";
                nodeChild = nodeParent;

            }

        }

        return new StringBuilder(morseTranslated).reverse().toString();
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public LinkedList<String> getCodes() {
        LinkedList<String> charList = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);


        while (!queue.isEmpty()) {
            Node node = queue.element();
            Node left = node.getLeft();
            Node right = node.getRight();

            if(left != null){
                queue.add(left);
            }
            if(right != null){
                queue.add(right);
            }
            if(node.getValue() != '!' && node.getValue() != '+' && node.getValue() != '/' && node.getValue() != '=' && node.getValue() != '*'){
                charList.add(charToMorse(node.getValue()));
            }


            queue.remove();

        }
        return charList;
    }

}