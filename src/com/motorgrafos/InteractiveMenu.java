package com.motorgrafos;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

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

            Graph graph = Graph.fromEdgeList(edges); // Construye el grafo a partir de las aristas
            graph.displayGraph(); // Mostrar el grafo

            boolean running = true; // Controla el bucle del menú
            while (running) {
                // Muestra las opciones disponibles
                System.out.println("\n--- OPCIONES ---");
                System.out.println("1. Calcular grado de un vértice");
                System.out.println("2. Verificar tipo de grafo (simple/multigrafo)");
                System.out.println("3. Verificar si es grafo completo");
                System.out.println("4. Verificar si el grafo es conexo");
                System.out.println("5. Mostrar componentes conexos");
                System.out.println("6. Volver a mostrar el grafo");
                System.out.println("7. Salir");
                System.out.print("Elige una opción: ");

                int option = scanner.nextInt(); // Lee la opción del usuario

                switch (option) {
                    case 1: // Calcular grado
                        System.out.print("Ingresa el índice del vértice: ");
                        int vertex = scanner.nextInt();
                        try {
                            int degree = graph.calculateDegree(vertex);
                            System.out.println("Grado del vértice " + vertex + ": " + degree);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2: // Verificar tipo de grafo
                        System.out.println("Resultado: " + graph.getGraphType());
                        break;

                    case 3: // Verificar si es completo
                        boolean complete = graph.isCompleteGraph();
                        System.out.println("¿El grafo es completo? " + (complete ? "SÍ" : "NO"));
                        break;

                    case 4: // Verificar si es conexo
                        boolean connected = graph.isConnected();
                        if (connected) {
                            System.out.println("El grafo es CONEXO - Se puede llegar de cualquier vertice a otro ");
                        } else {
                            System.out.println("El grafo no es CONEXO - Existen vertices aislados o ciclos");
                        }
                        break;

                    case 5: // Mostrar componentes conexos
                        List<Set<Integer>> components = graph.findConnectedComponents();
                        System.out.println("Componentes conexos encontrados: " + components.size());
                        int compNumber = 1;
                        for (Set<Integer> component : components) {
                            System.out.println(" Componente " + compNumber + ":" + component);
                            compNumber++;
                        }
                        break;

                    case 6: // Mostrar grafo nuevamente
                        graph.displayGraph();
                        break;

                    case 7: // Salir del programa
                        running = false;
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }
            }

        } catch (IOException e) { //Se ejecuta si el archivo no existe.
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        scanner.close();
    }
}
