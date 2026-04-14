# Spring REST MVC

* [Generics](#i-get0-vs-getfirst)
* [Sequenced Collections](#ii-sequenced-collections)

# I. `get(0)` vs `getFirst()`

* `get(0)`: É a forma legada (disponível desde o Java 1.2) para acessar o primeiro elemento de uma lista pelo índice.
* `getFirst()`: Introduzido no Java 21 como parte da nova API de _Sequenced Collections_, serve para obter o primeiro
  elemento de forma explícita, sem precisar de índices.

## 1. Tabela Comparativa

| Características        | `get(0)`                       | `getFirst()`                             |
|------------------------|--------------------------------|------------------------------------------|
| Disponibilidade        | Desde o Java 1.2               | A partir do Java 21                      |
| Comportamento se vazio | `IndexOutOfBoundsException`    | `NoSuchElementException`                 |
| Legibilidade           | Média (exige pensar no índice) | Alta (a inteção é explícita)             |
| Uso em Listas          | Sim (`ArrayList`, etc.)        | Sim (e em outras `SequencedCollections`) |
| Internamente           | Retorna o elemento `0`         | Chama `get(0)` (na `ArrayList`)          |

## 2. Detalhes Importantes

### 2.1. Semântica e Intenção

* `list.getFirst()` deixa claro que você quer o primeiro elemento.
* `list.get(0)` pode ser confundido com outras operações de indexação.
* `getFirst()` é parte de uma API moderna (que inclui `getLast()`, `reversed()`) que unifica como trabalhamos com
  coleções ordenadas.

### 2.2. Exceções em Listas Vazias

* Se a lista estiver vazia, ambos os métodos lançam exceções, mas de tipos diferentes:
    * `list.get(0)` -> `java.lang.IndexOutOfBoundsException`.
    * `list.getFirst()` -> `java.util.NoSuchElementException`.

### 2.3. Compatibilidade

* Se você estiver em um projeto Spring com Java 17 ou inferior, o `getFirst()` **não funcionará** (erro de compilação).
* No Java 21, `ArrayList.getFirst()` internamente chama `get(0)`, o que significa que não há ganho de performance,
  apenas de legibilidade

## 3. Exemplo no Spring (Contexto)

```java
// Antigo (Java 17 ou inferior)
String primeiro = minhaLista.get(0);

// Moderno (Java 21+)
String primeiro = minhaLista.getFirst();
```

## 4. Conclusão

Use `getFirst()` se o seu projeto estiver utilizando **Java 21 ou superior** para tornar o código mais limpo e legível.
Se estiver em uma versão anterior, `get(0)` é a alternativa correta.

# II. Sequenced Collections

As _Sequenced Collections_, introduzidas no Java 21 (JEP 431), são interfaces que padronizam o acesso à ordem dos
elementos em coleções ordenadas, permitindo manipular o início e o fim facilmente. As três novas interfaces são:

- `SequencedCollection`
- `SequencedSet`
- `SequencedMap`

## 1. Principais Interfaces e Características

### 1.1. `SequencedCollection`

Adiciona métodos como:

* `addFirst()`
* `addLast()`
* `getFirst()`
* `getLast()`
* `removeFirst()`
* `removeLast()`

### 1.2. `SequencedSet`

Aplicado a sets ordenados (como `LinkedHashSet`), permitindo manipulação ordenada sem depender de listas.

### 1.3. `SequencedMap`

Aplicado a maps ordenados (como `LinkedHashMap`), oferecendo métodos para acessar o primeiro/último par chave-valor.

### 1.4. `reversed()`

Método comum a todas que retorna uma visualização invertida da coleção, sem criar uma cópia.

## 2. Conclusão

Essa funcionalidade simplifica operações que antes exigiam _castings_ ou estruturas mais complexas, padronizando
comportamentos entre `List`, `Deque` e conjuntos/mapas ordenados. 