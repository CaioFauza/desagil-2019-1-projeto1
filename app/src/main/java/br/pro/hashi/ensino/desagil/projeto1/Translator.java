package br.pro.hashi.ensino.desagil.projeto1;

import java.util.HashMap;
import java.util.LinkedList;

// Não é permitido mudar nada nessa classe
// exceto o recheio dos três métodos.

public class Translator {
    private final Node root;
    private final HashMap<Character, Node> map;


    // Você deve mudar o recheio deste construtor,
    // de acordo com os requisitos não-funcionais.
    public Translator() {
        map = new HashMap<>();
        root = new Node('!');

        // Linha1
        Node nodeE = new Node('e');
        Node nodeT = new Node('t');
        root.setLeft(nodeE);
        root.setRight(nodeT);
        map.put('e', nodeE);
        map.put('t', nodeT);

        // Linha 2
        Node nodeI = new Node('i');
        Node nodeA = new Node('a');
        nodeE.setLeft(nodeI);
        nodeE.setRight(nodeA);
        Node nodeN = new Node('n');
        Node nodeM = new Node('m');
        nodeT.setLeft(nodeN);
        nodeT.setRight(nodeM);
        map.put('i', nodeI);
        map.put('a', nodeA);
        map.put('n', nodeN);
        map.put('m', nodeM);

        // Linha 3
        Node nodeS = new Node('s');
        Node nodeU = new Node('u');
        nodeI.setLeft(nodeS);
        nodeI.setRight(nodeU);
        Node nodeR = new Node('r');
        Node nodeW = new Node('w');
        nodeA.setLeft(nodeR);
        nodeA.setRight(nodeW);
        Node nodeD = new Node('d');
        Node nodeK = new Node('k');
        nodeN.setLeft(nodeD);
        nodeN.setRight(nodeK);
        Node nodeG = new Node('g');
        Node nodeO = new Node('o');
        nodeM.setLeft(nodeG);
        nodeM.setRight(nodeO);
        map.put('s', nodeI);
        map.put('u', nodeI);
        map.put('r', nodeA);
        map.put('w', nodeA);
        map.put('d', nodeN);
        map.put('k', nodeN);
        map.put('g', nodeM);
        map.put('o', nodeM);

        // Linha 4
        Node nodeH = new Node('h');
        Node nodeV = new Node('v');
        nodeS.setLeft(nodeH);
        nodeS.setRight(nodeV);
        Node nodeF = new Node('f');
        Node node4blank = new Node('!');
        nodeU.setLeft(nodeF);
        nodeU.setRight(node4blank);
        Node nodeL = new Node('l');
        Node node4blank1 = new Node('!');
        nodeR.setLeft(nodeL);
        nodeR.setRight(node4blank1);
        Node nodeP = new Node('p');
        Node nodeJ = new Node('j');
        nodeW.setLeft(nodeP);
        nodeW.setRight(nodeJ);
        Node nodeB = new Node('b');
        Node nodeX =new Node('x');
        nodeD.setLeft(nodeB);
        nodeD.setRight(nodeX);
        Node nodeC = new Node('c');
        Node nodeY = new Node('y');
        nodeK.setLeft(nodeC);
        nodeK.setRight(nodeY);
        Node nodeZ = new Node('z');
        Node nodeQ = new Node('q');
        nodeG.setLeft(nodeZ);
        nodeG.setRight(nodeQ);
        Node node4blank2 = new Node('!');
        Node node4blank3 = new Node('!');
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
        nodeH.setLeft(node5);
        nodeH.setRight(node4);
        Node node5blank = new Node('!');
        Node node3 = new Node ('3');
        nodeV.setLeft(node5blank);
        nodeV.setRight(node3);
        Node node5blank1 = new Node('!');
        Node node5blank2 = new Node('!');
        nodeF.setLeft(node5blank1);
        nodeF.setRight(node5blank2);
        Node node5blank3 = new Node('!');
        Node node2 = new Node('2');
        node4blank.setLeft(node5blank3);
        node4blank.setRight(node2);
        Node node5blank4 = new Node('!');
        Node node5blank5 = new Node('!');
        nodeL.setLeft(node5blank4);
        nodeL.setRight(node5blank5);
        Node nodeplus = new Node('+');
        Node node5blank6 = new Node('!');
        node4blank1.setLeft(nodeplus);
        node4blank1.setRight(node5blank6);
        Node node5blank7 = new Node('!');
        Node node5blank8 = new Node('!');
        nodeP.setLeft(node5blank7);
        nodeP.setRight(node5blank8);
        Node node5blank9 = new Node('!');
        Node node1 = new Node('1');
        nodeJ.setLeft(node5blank9);
        nodeJ.setRight(node1);
        Node node6 = new Node('6');
        Node nodeequal = new Node('=');
        nodeB.setLeft(node6);
        nodeB.setRight(nodeequal);
        Node nodebar = new Node('/');
        Node node5blank10 = new Node('!');
        nodeX.setLeft(nodebar);
        nodeX.setRight(node5blank10);
        Node node5blank11 = new Node('!');
        Node node5blank12 = new Node('!');
        nodeC.setLeft(node5blank11);
        nodeC.setRight(node5blank12);
        Node node5blank13 = new Node('!');
        Node node5blank14 = new Node('!');
        nodeY.setLeft(node5blank13);
        nodeY.setRight(node5blank14);
        Node node7 = new Node('7');
        Node node5blank15 = new Node('!');
        nodeZ.setLeft(node7);
        nodeZ.setRight(node5blank15);
        Node node5blank16 = new Node('!');
        Node node5blank17 = new Node('!');
        nodeQ.setLeft(node5blank16);
        nodeQ.setRight(node5blank17);
        Node node8 = new Node('8');
        Node node5blank18 = new Node('!');
        node4blank2.setLeft(node8);
        node4blank2.setRight(node5blank18);
        Node node9 = new Node('9');
        Node node0 = new Node('0');
        node4blank3.setLeft(node9);
        node4blank3.setRight(node0);
        map.put('5', nodeH);
        map.put('4', nodeV);
        map.put('3', nodeF);
        map.put('2', nodeL);
        map.put('+', nodeP);
        map.put('1', nodeJ);
        map.put('6', nodeB);
        map.put('=', nodeX);
        map.put('/', nodeC);
        map.put('7', nodeY);
        map.put('8', nodeZ);
        map.put('9', nodeQ);
        map.put('0', nodeQ);





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
        return " ";
    }


    // Você deve mudar o recheio deste método, de
    // acordo com os requisitos não-funcionais.
    public LinkedList<String> getCodes() {
        return new LinkedList<>();
    }

}
