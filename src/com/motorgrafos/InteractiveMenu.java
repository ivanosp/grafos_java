package com.motorgrafos;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InteractiveMenu
{
    public InteractiveMenu(){
        /* Metodo constructor */
    };

    public static void  startMenu() {
        /*
        * Leemos la ruta del archivo que ingresa el usuario
        * */
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MOTOR DE PROCESAMIENTO DE GRAFOS ===");
        System.out.print("Ingresa la ruta del archivo del grafo: ");
        String filePath = scanner.nextLine();

        try {
            /*
            * Leemos cada de las lineas del archivo que nos indica el usuario.
            * */
            List<FileReaderGraph.Edge> edges = FileReaderGraph.readGraphFromFile(filePath);
            System.out.println("\n✓ Archivo leído correctamente. Aristas encontradas: " + edges.size());

            Graph graph = Graph.fromEdgeList(edges);
            graph.displayGraph();

            boolean running = true;
            while (running) {
                System.out.println("\n--- OPCIONES ---");
                System.out.println("1. Calcular grado de un vértice");
                System.out.println("2. Verificar tipo de grafo (simple/multigrafo)");
                System.out.println("3. Verificar si es grafo completo");
                System.out.println("4. Volver a mostrar el grafo");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Ingresa el índice del vértice: ");
                        int vertex = scanner.nextInt();
                        try {
                            int degree = graph.calculateDegree(vertex);
                            System.out.println("Grado del vértice " + vertex + ": " + degree);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Resultado: " + graph.getGraphType());
                        break;

                    case 3:
                        boolean complete = graph.isCompleteGraph();
                        System.out.println("¿El grafo es completo? " + (complete ? "SÍ" : "NO"));
                        break;

                    case 4:
                        graph.displayGraph();
                        break;

                    case 5:
                        running = false;
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        scanner.close();
    }
}
