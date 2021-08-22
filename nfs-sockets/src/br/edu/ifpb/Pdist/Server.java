package br.edu.ifpb.Pdist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Server {

	public static void main(String[] args) throws IOException {
		System.out.println("== Servidor ==");

		ServerSocket serverSocket = new ServerSocket(7001);
		Socket socket = serverSocket.accept();

		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream dis = new DataInputStream(socket.getInputStream());

		while (true) {
			System.out.println("Cliente: " + socket.getInetAddress());

			String mensagem = dis.readUTF();

			switch (mensagem) {

			case "readdir":
				String files = "";
				File diretorio = new File("/home/andreia/IFPB/P6/PDist/nfs-sockets/files-testes");
				File[] namesFlies = diretorio.listFiles();

				for (File name : namesFlies) {
					files += name.getName() + " ";
				}
				dos.writeUTF(files);
				break;

			case "rename":
				File nameFile = new File("/home/andreia/IFPB/P6/PDist/nfs-sockets/files-testes/teste-file.txt");
                File fileNewName = new File("/home/andreia/IFPB/P6/PDist/nfs-sockets/files-testes/rename-test.txt");

                if(nameFile.exists()) {
                	nameFile.renameTo(fileNewName);
                    dos.writeUTF("Arquivo renomeado");
                } else {
                    dos.writeUTF("Esse arquivo não existe nesse diretório.");
                }

			case "create":
				String fileName = "myfile_" + UUID.randomUUID().toString() + ".txt";
				Path p = Paths.get("/home/andreia/IFPB/P6/PDist/nfs-sockets/files-testes" + fileName);
				Files.createFile(p);
				dos.writeUTF("Arquivo Criado com sucesso");
				break;

			case "remove":
				Path fileDelete = Paths.get("/home/andreia/IFPB/P6/PDist/nfs-sockets/files-testes/teste-file.txt");
				if (Files.exists(fileDelete)) {
					Files.delete(fileDelete);
					dos.writeUTF("Arquivo Removido com sucesso");
				}else {
					dos.writeUTF("Esse arquivo não existe nesse diretório");
				}
				break;

			}
		}
	}
}
