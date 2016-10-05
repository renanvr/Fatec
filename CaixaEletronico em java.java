package vetormatriz;

import javax.swing.JOptionPane;

public class CaixaEletronico {

	public static void main(String[] args) {

		int[] maior = new int[4];
		int[] menor = new int[4];
		int[] total = new int[4];
		int[] media = new int[4];
		int[] cont = new int[4];
		int[] cassete = new int[6];

		int sobra = 0;
		int selecionar = 0;
		int i = 0;

		while (selecionar != 9) {

			selecionar = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite a Opção desejada: \n\n 1 - Carregar Notas \n 2 - Retirar Notas \n 3 - Estatísticas \n 9 - Fim"));

			switch (selecionar) {

			case 1:
				carregar(cassete);
				break;

			case 2:
				i++;
				retirar(cassete, maior, menor, total, media, cont, i);

				break;

			case 3:
				estatistica(maior, menor, total, sobra, cassete, media);
				break;

			case 9:
				fim();
				break;

			default:
				JOptionPane.showMessageDialog(null, " Opção Inválida");

			}

		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void carregar(int cassete[]) {

		cassete[0] = Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de notas de $2 a ser abastecida"));
		cassete[1] = Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de notas de $5 a ser abastecida"));
		cassete[2] = Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de notas de $10 a ser abastecida"));
		cassete[3] = Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de notas de $20 a ser abastecida"));
		cassete[4] = Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de notas de $50 a ser abastecida"));
		cassete[5] = Integer
				.parseInt(JOptionPane.showInputDialog("Digite a quantidade de notas de $100 a ser abastecida"));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void retirar(int cassete[], int maior[], int menor[], int total[], int media[], int cont[], int i) {

		if (i < 100) {

			int x = 0;
			int[] retorno = new int[4];
			int banco = 0;

			while ((banco != 1) && (banco != 2) && (banco != 3) && (banco != 4)) {

				banco = Integer.parseInt(JOptionPane.showInputDialog(
						"Digite o seu Banco: \n 1 - Banco do Brasil \n 2 - Santander \n 3 - Itaú \n 4 - Caixa."));

				switch (banco) {

				case 1:
					x = 0;

					break;

				case 2:
					x = 1;
					break;

				case 3:
					x = 2;
					break;

				case 4:
					x = 3;
					break;

				default:

					JOptionPane.showMessageDialog(null, "Número do banco inválido");
					break;
				}
			}

			int valor = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor que deseja sacar"));
			int saque = valor;

			if ((valor == 3) || (valor == 1)) {

				JOptionPane.showMessageDialog(null, "Valor não permitido");

			} else if

			((cassete[0] * 2) + (cassete[1] * 5) + (cassete[2] * 10) + (cassete[3] * 20) + (cassete[4] * 50)
					+ (cassete[5] * 100) < valor) {

				JOptionPane.showMessageDialog(null, "Valor excedeu o limite do caixa");

			} else {

				while ((valor >= 100) && (cassete[5] > 0) && (valor != 101) && (valor != 103)) {
					cassete[5]--;
					valor = valor - 100;
					retorno[3]++;

				}

				while ((valor >= 50) && (cassete[4] > 0) && (valor != 51) && (valor != 53)) {
					cassete[4]--;
					valor = valor - 50;
					retorno[2]++;
				}

				while ((valor >= 20) && (cassete[3] > 0) && (valor != 21) && (valor != 23)) {
					cassete[3]--;
					valor = valor - 20;
					retorno[1]++;
				}

				while ((valor >= 10) && (valor != 13) && (valor != 11) && (cassete[2] > 0)) {
					cassete[2]--;
					valor = valor - 10;
					retorno[0]++;
				}

				if ((valor == 13) && (cassete[1] < 1) && (cassete[0] < 4)
						|| (valor == 11) && (cassete[1] < 1) && (cassete[0] < 3)
						|| (valor == 9) && (cassete[1] < 1) && (cassete[0] < 2) || (valor == 8) && (cassete[0] < 4)
						|| (valor == 7) && (cassete[1] < 1) && (cassete[0] < 1) || (valor == 6) && (cassete[0] < 3)
						|| (valor == 5) && (cassete[1] < 1) || (valor == 4) && (cassete[0] < 2)
						|| (valor == 2) && (cassete[0] < 1)) {

					cassete[5] = cassete[5] + retorno[3];
					cassete[4] = cassete[4] + retorno[2];
					cassete[3] = cassete[3] + retorno[1];
					cassete[2] = cassete[2] + retorno[0];
					retorno[0] = 0;
					retorno[1] = 0;
					retorno[2] = 0;
					retorno[3] = 0;

					JOptionPane.showMessageDialog(null, "Não há troco para o valor digitado \n Escolha outro valor.");

				} else {

					while ((valor >= 5) && (valor != 8) && (valor != 6)) {
						cassete[1]--;
						valor = valor - 5;

					}

					while (valor >= 2) {
						cassete[0]--;
						valor = valor - 2;
					}

					if (valor == 0) {

						JOptionPane.showMessageDialog(null, +saque + " Sacado com sucesso");

						total[x] = total[x] + saque;
						cont[x]++;
						media[x] = total[x] / cont[x];

						if ((maior[x] == 0) || (saque > maior[x])) {
							maior[x] = saque;
						}

						if ((menor[x] == 0) || (saque < menor[x])) {
							menor[x] = saque;

						}
					}
				}
			}

		} else {

			JOptionPane.showMessageDialog(null, "Limite de 100 saques diários excedido");
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void estatistica(int maior[], int menor[], int total[], int sobra, int cassete[], int media[]) {

		JOptionPane.showMessageDialog(null,
				"Banco do Brasil \n\n O maior valor sacado foi " + maior[0] + "\n O menor valor sacado foi " + menor[0]
						+ "\n O valor total dos saques foi " + total[0] + "\n A média dos saques foi " + media[0]);

		JOptionPane.showMessageDialog(null,
				"Santander \n\n O maior valor sacado foi " + maior[1] + "\n O menor valor sacado foi " + menor[1]
						+ "\n O valor total dos saques foi " + total[1] + "\n A média dos saques foi " + media[1]);

		JOptionPane.showMessageDialog(null,
				"Itaú \n\n O maior valor sacado foi " + maior[2] + "\n O menor valor sacado foi " + menor[2]
						+ "\n O valor total dos saques foi " + total[2] + "\n A média dos saques foi " + media[2]);

		JOptionPane.showMessageDialog(null,
				"Caixa \n\n O maior valor sacado foi " + maior[3] + "\n O menor valor sacado foi " + menor[3]
						+ "\n O valor total dos saques foi " + total[3] + "\n A média dos saques foi " + media[3]);

		sobra = ((cassete[0] * 2) + (cassete[1] * 5) + (cassete[2] * 10) + (cassete[3] * 20) + (cassete[4] * 50)
				+ (cassete[5] * 100));

		JOptionPane.showMessageDialog(null, "O valor da sobra do Caixa foi de: $" + sobra + ",00");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static void fim() {

		JOptionPane.showMessageDialog(null, "Programa finalizado com sucesso");
	}

}
