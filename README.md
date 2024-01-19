<p align="center">
    <img src="Imagens\capaDoProjeto.png">
    <br>
    <h1 align="center">
    🗃️ SERVIÇO WEB DE PEDIDOS | API REST FULL 🗃️
    </h1>
</p>
<br>
<h2>
    :mag: DOWNLOAD DO PROJETO NO LINK ABAIXO
</h2>

```
INSERIR...
``` 
<h2>
    📑 SUMÁRIO
</h2>

1. [INTRODUÇÃO](#1-introdução)<br>
   1.1 - [Intuito do Projeto](#11-intuito-do-projeto)<br>
   1.2 - [Ferramentas Utilizadas](#12-ferramentas-utilizadas)
2. [DESENVOLVIMENTO](#2-desenvolvimento)<br>
   2.1 - [Demonstração Ilustrativa do Mapeamento Objeto Relacional](#21-demonst-mapeamento-objeto-relacional)<br>
   2.2 - [Demonstração Ilustrativa do Banco de Dados Modelo Relacional](#22-banco-de-dados-modelo-relacional)
3. [Conclusão](#3-conclusão)

## 1 INTRODUÇÃO

### 1.1 Intuito do Projeto

<p>
    O intuito do projeto é mostrar a criação completa do back-end de um serviço web que insere, deleta, atualiza e realiza consultas em um sistema de vendas.<br>
	Esse serviço é uma JPA(Java Persistence API) que fornece uma maneira padronizada de interagir com bancos de dados relacionais usando objetos Java.
</p>

### 1.2 Ferramentas Utilizadas

1.2.1 **Java**
   - Linguagem de programação usada para desenvolver a aplicação.

1.2.3 **Spring Framework(Boot, MVC e Web)**
   - Usado para facilitar o desenvolvimento da aplicação através de inversão de controle(fazendo com que a execução de um trecho de código não seja controlada pelo próprio código, mas sim por um framework ou container) e injeção de dependência(permite que as dependências de uma classe sejam fornecidas por meio de injeção em vez de serem instanciadas diretamente pela própria classe).<br>

1.2.4 **Hibernate Framework**
   - Framework de mapeamento objeto-relacional (ORM). Simplifica a interação com bancos de dados relacionais, permitindo a manipulação de dados em objetos Java, sem se preocupar diretamente com a lógica de persistência. O Hibernate realiza a correspondência entre objetos Java e tabelas de banco de dados, gerenciando automaticamente operações como inserção, atualização, exclusão e seleção, ou seja, é o responsável por executar as consultas JPQL( linguagem de consulta orientada a objetos).

1.2.5 **IDE Eclipse**
   - Software utilizado para programar(escrever os códigos) compilar e startar a aplicação.

1.2.6 **Banco de Dados PostgreSQL**
   - Banco de Dados responsável por armazenar os dados que são inseridos, deletados, atualizados e consultados pela aplicação.

1.2.7 **SGBD pgAdmin**
   - Sistema de Gerenciamento de Banco de Dados usado para interagir com o Banco de Dados, basicamente é uma interface que nos permite realizar operações e essas operações são refletidas no Banco de Dados.

1.2.8 **Postman**
   - Software utilizado para testar se a API REST FULL está funcionando ou não, através desse software serão feitas requisições CRUD'S que no caso são: GET(SELECT), POST(INSERT), DELETE(DELETE) e PUT(UPDATE), estas requisições serão enviadas para a API REST FULL que é responsável por receber a requisição e executa-la, depois dar um retorno, esse retorno será exibido no Postman e os dados estarão dentro de um acrônimo JSON.

1.2.9 **Notepad ++**
   -  Editor de texto e de código fonte de código aberto usado para fazer anotações.

### 1.3 Comunicação das Classes em Relação a Funcionalidade da Aplicação

<p>
A ilustração abaixo mostra como as classes irão se comunicar durante a funcionalidade da aplicação.<br>
Quando dado o start o controlador chama o serviço que age no repositório, essa ação reflete na entidade.<br>
Segue a lista que explica cada grupo de classe:

- Controlador

<p>
O controlador é a camada responsável por receber as requisições do usuário e responder com a resposta apropriada. Ele é a ponte entre o mundo da aplicação e o mundo do usuário.
</p>

- Serviço

<p>
O serviço é a camada responsável por implementar a lógica de negócio da aplicação. Ele é responsável por executar as operações necessárias para atender às requisições do usuário.<br>
Caso o serviço seja chamado e algo dê errado uma excessão será apresentada, então a excessão é chamada a partir da execução do serviço quando um erro acontece.
</p>

- Repositório

<p>
O repositório é a camada responsável por acessar os dados do banco de dados. Ele é responsável por fornecer acesso aos dados da aplicação de forma segura e eficiente.
</p>

- Entidade

<p>
A entidade é uma representação de um dado no banco de dados. Ela é responsável por armazenar os dados da aplicação de forma estruturada.<br>
Existe especificamente uma interface que auxilia uma classe, é a <strong>InterfaceOrderItem</strong>, essa interface foi criada porque o repositório precisa acessar os dados diretamente para fazer um SELECT específico, mas isso é só no caso do item do pedido.
</p>

<p align="center">
    <img src="Imagens\funcionalidadeDaAplicacao.png">
</p>

### 1.3 ORM (Mapeamento Objeto Relacional)

<p>
Segue abaixo a demonstração de como irá funcionar o ORM.<br>
Uma entidade com atributos se torna uma tabela com colunas no BD quando a aplicação é iniciada.<br>
O Framework <strong>Hibernate</strong> cria e mantém as tabelas do BD com base nas entidades JPA definidas no código, na imagem é possível saber qual entidade está ligada a qual tabela e qual atributo está ligado a qual coluna.
<p>

<p align="center">
    <img src="Imagens\ORM.png">
</p>

### 1.4 Modelo Relacional do Banco de Dados 

<p>
A imagem abaixo tem como objetivo mostrar como as tabelas do BD irão se relacionar.<br>
</p>

<p align="center">
    <img src="Imagens\relacionalDatabase.png">
</p>

<p>

- Muitos produtos para 1 categoria
- Muitos pedidos para 1 usuário
- Muitos itens de pedido para 1 produto
- Muitos itens de pedido para 1 pedido
- Muitos produtos para 1 categoria
</p>

## 2 DESENVOLVIMENTO

<p>
Iniciamos o desenvolvimento do código dentro da IDE <strong>Eclipse</strong>
<p>

### Criando Classe que Starta a Aplicação

#### ProjectApplication

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

<p>
Colocamos a anotação <strong>@SpringBootApplication</strong> encima da classe <strong>ProjectApplication</strong> para  marcar uma classe como a classe principal de uma aplicação Spring Boot.
</p>

```java
@SpringBootApplication
public class ProjectApplication {
```

<p>
O método <strong>main</strong> é o método que starta a aplicação. Este é o ponto de entrada onde a execução da aplicação começa.
</p>

```java
public static void main(String[] args) {
	SpringApplication.run(ProjectApplication.class, args);
}
```
```java
}
```

### 2.1 Criando Entidades

<p>
Vamos criar as entidades do nosso projeto, cada uma delas tem seus atributos, isso no BD irá refletir como as tabelas que tem suas colunas.
</p>

#### 2.1.1 Category

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
```

<p>
A anotação <strong>@Entity</strong> define que a classe será uma entidade persistente, indicando que os objetos dessa classe podem ser mapeados para registros em um BD relacional.<br>
A anotação <strong>@Table(name = "tb_category")</strong> é usada para especificar detalhes adicionais sobre a tabela associada a uma entidade JPA (Java Persistence API) e definir o nome da tabela no BD.<br>
A variável <strong>serialVersionUID</strong> recebe o valor <strong>1L</strong> que no caso é o código longo literal, ou seja, o serial do objeto.
</p>

```java
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
```

<p>
Agora vamos definir os atributos da classe, que no caso serão as colunas da tabela no BD.<br>
A anotação <strong>@Id</strong> define que o atributo <strong>id</strong> será um id no BD e a anotação <strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong> define que esse id será gerado automaticamente, quanto ao atributo <strong>name</strong> este será uma coluna comum.
</p>

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
```

<p>
Criamos uma lista de produtos dentro da entidade <strong>Category</strong> porque cada produto terá uma categoria e inserimos a anotação <strong>@JsonIgnore</strong> que serve para ignorar o JSON da lista de produtos, ou seja, quando uma requisição for feita solicitando todas as categorias a lista de produtos será ignorada e serão exibidas apenas as categorias, também inserimos a anotação <strong>@OneToMany(mappedBy = "categoryProduct")</strong> para definir um relacionamento de "um-para-muitos" onde o atributo <strong>categoryProduct</strong> que fica na classe <strong>Product</strong> se refere a uma categoria, ou seja, cada produto tem uma categoria.
</p>

```java
@JsonIgnore
@OneToMany(mappedBy = "categoryProduct")
private List<Product> products = new ArrayList<>();
```

<p>
Criamos os contrutores padrões da classe, o primeiro construtor é padrão e sem parâmetros, é usado para instanciar um objeto sem fornecer valores específicos durante a criação, o segundo construtor que possui parâmetros é utilizado para instanciar um objeto e fornecer valores específicos para os atributos no momento da criação.
</p>

```java
public Category() {
}
	
public Category(Long id, String name) {
	super();
	this.id = id;
	this.name = name;
}
```

<p>
Depois criamos os métodos getters e setters que servem para pegar e setar os atributos.
</p>

```java
public Long getId() {
	return id;
}
	
public void setId(Long id) {
	this.id = id;
}
	
public String getName() {
	return name;
}
	
public void setName(String name) {
	this.name = name;
}
	
public List<Product> getProducts() {
	return products;
}
```
<p>
Fazemos uma sobreposição com a anotação <strong>@Override</strong> do método <strong>hashCode()</strong> para gerar um código hash específico para cada instância do objeto pertencente a classe.
</p>

```java
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
```
<p>
A anotação <strong>@Override</strong> está fazendo uma sobreposição no método <strong>equals</strong> que compara objetos da classe <strong>Category</strong> com base no conteúdo de seus atributos, especialmente no atributo <strong>id</strong>. Dois objetos <strong>Category</strong> serão considerados iguais se tiverem o mesmo <strong>id</strong> ou se ambos forem nulos.
</p>

```java
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Category other = (Category) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}
```

```java
}
```

#### 2.1.4 Product

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
```

<p>
A anotação <strong>@Entity</strong> define que a classe será uma entidade persistente, indicando que os objetos dessa classe podem ser mapeados para registros em um BD relacional.<br>
A anotação <strong>@Table(name = "tb_product")</strong> é usada para especificar detalhes adicionais sobre a tabela associada a uma entidade JPA (Java Persistence API) e definir o nome da tabela no BD.<br>
A variável <strong>serialVersionUID</strong> recebe o valor <strong>1L</strong> que no caso é o código longo literal, ou seja, o serial do objeto.
</p>

```java
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
```

<p>
Agora vamos definir os atributos da classe, que no caso serão as colunas da tabela no BD.<br>
A anotação <strong>@Id</strong> define que o atributo <strong>id</strong> será um id no BD e a anotação <strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong> define que esse id será gerado automaticamente, já os  atributos <strong>name</strong>,<strong>description</strong>, <strong>price</strong> e <strong>imgUrl</strong> serão colunas comuns.
</p>

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String description;
private Double price;
private String imgUrl;
```

<p>
O atributo <strong>categoryProduct</strong> contém duas anotações, a anotação <strong>@ManyToOne</strong> faz com que o atributo tenha uma relação de "muitos para um" fazendo com que o atributo fique do lado do "um", ou seja, muitos produtos para uma categoria, cada produto terá uma categoria, a anotação <strong>@JoinColumn(name = "category_id")</strong>(junção de coluna) faz com que o atributo seja uma chave estrangeira e nomeia a coluna, repare que o atributo <strong>categoryProduct</strong> é moldado pela classe <strong>Category</strong>, essa junção se torna possível porque na entidade <strong>Category</strong> existe uma lista de produtos mapeada.<br>
Isso no BD irá refletir como uma <strong>CONSTRAINT</strong>(limitação), pois a coluna <strong>category_id</strong> da tabela <strong>tb_product</strong> é uma FOREIGN KEY(chave estrangeira) que se refere a um objeto da tabela <strong>tb_category</strong> e esse objeto é identificado pelo seu <strong>id</strong>
</p>

```java
@ManyToOne
@JoinColumn(name = "category_id")
private Category categoryProduct;
```

<p>
Criamos uma lista de itens do pedido dentro da entidade <strong>Product</strong> porque cada item de pedido terá seu produto e inserimos a anotação <strong>@JsonIgnore</strong> que serve para ignorar o JSON da lista de itens do pedido, ou seja, quando uma requisição for feita solicitando todos os produtos a lista de itens do pedido será ignorada e serão exibidas apenas os produtos, também inserimos a anotação <strong>@OneToMany(mappedBy = "productId")</strong> para definir um relacionamento de "um para muitos" onde o atributo <strong>productId</strong> que fica na classe <strong>OrderItem</strong> se refere a um produto, ou seja, cada item de pedido tem um produto.
</p>

```java
@JsonIgnore
@OneToMany(mappedBy = "productId")
private List<OrderItem> orderItem = new ArrayList<>();
```

<p>
Criamos os contrutores padrões da classe, o primeiro construtor é padrão e sem parâmetros, é usado para instanciar um objeto sem fornecer valores específicos durante a criação, o segundo construtor que possui parâmetros é utilizado para instanciar um objeto e fornecer valores específicos para os atributos no momento da criação.
</p>

```java
public Product() {
}

public Product(Long id, String name, String description, Double price, String imgUrl, Category categoryProduct) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.price = price;
	this.imgUrl = imgUrl;
	this.categoryProduct = categoryProduct;
}
```

<p>
Depois criamos os métodos getters e setters que servem para pegar e setar os atributos.
</p>

```java
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public Double getPrice() {
    return price;
}

public void setPrice(Double price) {
    this.price = price;
}

public String getImgUrl() {
    return imgUrl;
}

public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
}

public Category getCategoryProduct() {
    return categoryProduct;
}

public void setCategoryProduct(Category categoryProduct) {
    this.categoryProduct = categoryProduct;
}

public List<OrderItem> getOrderItem() {
    return orderItem;
}
```

<p>
Fazemos uma sobreposição com a anotação <strong>@Override</strong> do método <strong>hashCode()</strong> para gerar um código hash específico para cada instância do objeto pertencente a classe.
</p>

```java
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
```
<p>
A anotação <strong>@Override</strong> está fazendo uma sobreposição no método <strong>equals</strong> que compara objetos da classe <strong>Product</strong> com base no conteúdo de seus atributos, especialmente no atributo <strong>id</strong>. Dois objetos <strong>Product</strong> serão considerados iguais se tiverem o mesmo <strong>id</strong> ou se ambos forem nulos.
</p>

```java
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Product other = (Order) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}
```
```java
}
```

#### 2.1.2 Order

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
```

<p>
A anotação <strong>@Entity</strong> define que a classe será uma entidade persistente, indicando que os objetos dessa classe podem ser mapeados para registros em um BD relacional.<br>
A anotação <strong>@Table(name = "tb_order")</strong> é usada para especificar detalhes adicionais sobre a tabela associada a uma entidade JPA (Java Persistence API) e definir o nome da tabela no BD.<br>
A variável <strong>serialVersionUID</strong> recebe o valor <strong>1L</strong> que no caso é o código longo literal, ou seja, o serial do objeto.
</p>

```java
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
```

<p>
Agora vamos definir os atributos da classe, que no caso serão as colunas da tabela no BD.<br>
A anotação <strong>@Id</strong> define que o atributo <strong>id</strong> será um id no BD e a anotação <strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong> define que esse id será gerado automaticamente, já os  atributos <strong>moment</strong> e <strong>orderStatus</strong> serão colunas comuns.
</p>

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Instant moment;
private String orderStatus;
```

<p>
O atributo <strong>client</strong> contém duas anotações, a anotação <strong>@ManyToOne</strong> faz com que o atributo tenha uma relação de "muitos para um" fazendo com que o atributo fique do lado do um, ou seja, muitos pedidos para um cliente, cada pedido terá um cliente, a anotação <strong>@JoinColumn(name = "client_id")</strong>(junção de coluna) faz com que o atributo seja uma chave estrangeira e nomeia a coluna, repare que o atributo <strong>client</strong> é moldado pela classe <strong>User</strong>, essa junção se torna possível porque na entidade <strong>User</strong> existe uma lista de pedidos mapeada.<br>
Isso no BD irá refletir como uma <strong>CONSTRAINT</strong>(limitação), pois a coluna <strong>client_id</strong> da tabela <strong>tb_order</strong> é uma FOREIGN KEY(chave estrangeira) que se refere a um objeto da tabela <strong> tb_user</strong> e esse objeto é identificado pelo seu <strong>id</strong>
</p>

```java
@ManyToOne
@JoinColumn(name = "client_id")
private User client;
```

<p>
Criamos uma lista de itens do pedido dentro da entidade <strong>Order</strong> porque cada item de pedido terá seu pedido e inserimos a anotação <strong>@JsonIgnore</strong> que serve para ignorar o JSON da lista de itens do pedido, ou seja, quando uma requisição for feita solicitando todos os pedidos a lista de itens do pedido será ignorada e serão exibidas apenas os pedidos, também inserimos a anotação <strong>@OneToMany(mappedBy = "orderId")</strong> para definir um relacionamento de "um para muitos" onde o atributo <strong>orderId</strong> que fica na classe <strong>OrderItem</strong> se refere a um pedido, ou seja, cada item de pedido tem um pedido.
</p>

```java
@JsonIgnore
@OneToMany(mappedBy = "orderId")
private List<OrderItem> orderItem = new ArrayList<>();
```

<p>
Criamos os contrutores padrões da classe, o primeiro construtor é padrão e sem parâmetros, é usado para instanciar um objeto sem fornecer valores específicos durante a criação, o segundo construtor que possui parâmetros é utilizado para instanciar um objeto e fornecer valores específicos para os atributos no momento da criação.
</p>

```java
public Order() {
}
	
public Order(Long id, Instant moment, String orderStatus, User client) {
    super();
    this.id = id;
    this.moment = moment;
    this.orderStatus = orderStatus;
    this.client = client;
}
```

<p>
Depois criamos os métodos getters e setters que servem para pegar e setar os atributos.
</p>

```java
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }
}
```
<p>
Fazemos uma sobreposição com a anotação <strong>@Override</strong> do método <strong>hashCode()</strong> para gerar um código hash específico para cada instância do objeto pertencente a classe.
</p>

```java
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
```
<p>
A anotação <strong>@Override</strong> está fazendo uma sobreposição no método <strong>equals</strong> que compara objetos da classe <strong>Order</strong> com base no conteúdo de seus atributos, especialmente no atributo <strong>id</strong>. Dois objetos <strong>Order</strong> serão considerados iguais se tiverem o mesmo <strong>id</strong> ou se ambos forem nulos.
</p>

```java
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Order other = (Order) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}
```

```java
}
```

#### 2.1.5 User

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
```

<p>
A anotação <strong>@Entity</strong> define que a classe será uma entidade persistente, indicando que os objetos dessa classe podem ser mapeados para registros em um BD relacional.<br>
A anotação <strong>@Table(name = "tb_user")</strong> é usada para especificar detalhes adicionais sobre a tabela associada a uma entidade JPA (Java Persistence API) e definir o nome da tabela no BD.<br>
A variável <strong>serialVersionUID</strong> recebe o valor <strong>1L</strong> que no caso é o código longo literal, ou seja, o serial do objeto.
</p>

```java
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
```

<p>
Agora vamos definir os atributos da classe, que no caso serão as colunas da tabela no BD.<br>
A anotação <strong>@Id</strong> define que o atributo <strong>id</strong> será um id no BD e a anotação <strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong> define que esse id será gerado automaticamente, já os demais atributos serão colunas comuns.
</p>

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String email;
private String phone;
private String password;
```

<p>
Criamos uma lista de pedidos dentro da entidade <strong>User</strong> porque cada pedido terá seu cliente e inserimos a anotação <strong>@JsonIgnore</strong> que serve para ignorar o JSON da lista de pedidos, ou seja, quando uma requisição for feita solicitando todos os usuários a lista de pedidos será ignorada e serão exibidas apenas os usuários, também inserimos a anotação <strong>@OneToMany(mappedBy = "client")</strong> para definir um relacionamento de "um para muitos" onde o atributo <strong>client</strong> que fica na classe <strong>Order</strong> se refere a uma usuário(cliente), ou seja, pedido tem um usuário(cliente).
</p>

```java
@JsonIgnore
@OneToMany(mappedBy = "client")
private List<Order> orders = new ArrayList<>();
```

<p>
Criamos os contrutores padrões da classe, o primeiro construtor é padrão e sem parâmetros, é usado para instanciar um objeto sem fornecer valores específicos durante a criação, o segundo construtor que possui parâmetros é utilizado para instanciar um objeto e fornecer valores específicos para os atributos no momento da criação.
</p>

```java
public User() {
}

public User(Long id, String name, String email, String phone, String password) {
    super();
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.password = password;
}
```

<p>
Depois criamos os métodos getters e setters que servem para pegar e setar os atributos.
</p>

```java
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public List<Order> getOrders() {
    return orders;
}
```

<p>
Fazemos uma sobreposição com a anotação <strong>@Override</strong> do método <strong>hashCode()</strong> para gerar um código hash específico para cada instância do objeto pertencente a classe.
</p>

```java
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
```
<p>
A anotação <strong>@Override</strong> está fazendo uma sobreposição no método <strong>equals</strong> que compara objetos da classe <strong>User</strong> com base no conteúdo de seus atributos, especialmente no atributo <strong>id</strong>. Dois objetos <strong>User</strong> serão considerados iguais se tiverem o mesmo <strong>id</strong> ou se ambos forem nulos.
</p>

```java
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    User other = (Order) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}
```
```java
}
```

#### 2.1.3 OrderItem

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
```

<p>
A anotação <strong>@Entity</strong> define que a classe será uma entidade persistente, indicando que os objetos dessa classe podem ser mapeados para registros em um BD relacional.<br>
A anotação <strong>@Table(name = "tb_order_item")</strong> é usada para especificar detalhes adicionais sobre a tabela associada a uma entidade JPA (Java Persistence API) e definir o nome da tabela no BD.<br>
A variável <strong>serialVersionUID</strong> recebe o valor <strong>1L</strong> que no caso é o código longo literal, ou seja, o serial do objeto.
</p>

```java
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
```

<p>
Agora vamos definir os atributos da classe, que no caso serão as colunas da tabela no BD.<br>
A anotação <strong>@Id</strong> define que o atributo <strong>id</strong> será um id no BD e a anotação <strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong> define que esse id será gerado automaticamente, já os  atributos <strong>quantity</strong> e <strong>price</strong> serão colunas comuns.
</p>

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Integer quantity;
private Double price;

```

<p>
O atributo <strong>productId</strong> contém duas anotações, a anotação <strong>@ManyToOne</strong> faz com que o atributo tenha uma relação de "muitos para um" fazendo com que o atributo fique do lado do "um", ou seja, muitos itens do pedido para um produto, cada item do pedido terá um produto, a anotação <strong>@JoinColumn(name = "product_id"")</strong>(junção de coluna) faz com que o atributo seja uma chave estrangeira e nomeia a coluna, repare que o atributo <strong>productId</strong> é moldado pela classe <strong>Product</strong>, essa junção se torna possível porque na entidade <strong>Product</strong> existe uma lista de itens do pedido mapeada.<br>
Isso no BD irá refletir como uma <strong>CONSTRAINT</strong>(limitação), pois a coluna <strong>product_id</strong> da tabela <strong>tb_order_item</strong> é uma FOREIGN KEY(chave estrangeira) que se refere a um objeto da tabela <strong> tb_product</strong> e esse objeto é identificado pelo seu <strong>id</strong>
</p>

```java
@ManyToOne
@JoinColumn(name = "product_id")
private Product productId;
```

<p>
O atributo <strong>orderId</strong> contém duas anotações, a anotação <strong>@ManyToOne</strong> faz com que o atributo tenha uma relação de "muitos para um" fazendo com que o atributo fique do lado do "um", ou seja, muitos itens do pedido para um pedido, cada item do pedido terá um pedido, a anotação <strong>@JoinColumn(name = "order_id")</strong>(junção de coluna) faz com que o atributo seja uma chave estrangeira e nomeia a coluna, repare que o atributo <strong>orderId</strong> é moldado pela classe <strong>Order</strong>, essa junção se torna possível porque na entidade <strong>Order</strong> existe uma lista de itens do pedido mapeada.<br>
Isso no BD irá refletir como uma <strong>CONSTRAINT</strong>(limitação), pois a coluna <strong>order_id</strong> da tabela <strong>tb_order_item</strong> é uma FOREIGN KEY(chave estrangeira) que se refere a um objeto da tabela <strong> tb_order</strong> e esse objeto é identificado pelo seu <strong>id</strong>
</p>

```java
@ManyToOne
@JoinColumn(name = "order_id")
private Order orderId;
```

<p>
Criamos os contrutores padrões da classe, o primeiro construtor é padrão e sem parâmetros, é usado para instanciar um objeto sem fornecer valores específicos durante a criação, o segundo construtor que possui parâmetros é utilizado para instanciar um objeto e fornecer valores específicos para os atributos no momento da criação.
</p>

```java
public OrderItem() {
}

public OrderItem(Long id, Integer quantity, Double price, Product productId, Order orderId) {
    super();
	this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.productId = productId;
    this.orderId = orderId
}
```

<p>
Depois criamos os métodos getters e setters que servem para pegar e setar os atributos.
</p>

```java
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Integer getQuantity() {
    return quantity;
}

public void setQuantity(Integer quantity) {
    this.quantity = quantity;
}

public Double getPrice() {
    return price;
}

public void setPrice(Double price) {
    this.price = price;
}

public Product getProductId() {
    return productId;
}

public void setProductId(Product productId) {
    this.productId = productId;
}

public Order getOrderId() {
    return orderId;
}

public void setOrderId(Order orderId) {
    this.orderId = orderId;
}

public Double getSubTotal() {
    return price * quantity;
}
```

<p>
Fazemos uma sobreposição com a anotação <strong>@Override</strong> do método <strong>hashCode()</strong> para gerar um código hash específico para cada instância do objeto pertencente a classe.
</p>

```java
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
```
<p>
A anotação <strong>@Override</strong> está fazendo uma sobreposição no método <strong>equals</strong> que compara objetos da classe <strong>OrderItem</strong> com base no conteúdo de seus atributos, especialmente no atributo <strong>id</strong>. Dois objetos <strong>OrderItem</strong> serão considerados iguais se tiverem o mesmo <strong>id</strong> ou se ambos forem nulos.
</p>

```java
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    OrderItem other = (Order) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}
```
```java
}
```

### 2.1 Criando Repositórios

<p>
São as classes responsáveis por acessar e manipular os dados no BD. Essas classes armazenam as querys que são executadas pelo <strong>Hibernate</strong>.
</p>

#### ProductRepository

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.Product;
```

<p>
Depois criamos a interface <strong>ProductRepository</strong> que se extende a interface <strong>JpaRepository</strong>(simplifica o desenvolvimento de operações de acesso a dados em BD's relacionais usando JPA, interface útil para operações CRUD padrão e consultas dinâmicas) que recebe como parâmetro as entidades <strong>Order</strong> e <strong>Long</strong>, ou seja, irá aceitar objetos desse tipo.
</p>

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
```

<p>
Dentro da interface inserimos a anotação <strong>@Query</strong> que serve para indicar a criação de uma query que recebe como valor uma consulta JPQL(consulta orientada a objetos), a consulta está solicitando todas as instâncias da entidade <strong>Product</strong>, o alias <strong>obj</strong> é usado para se referir a cada instância de <strong>Product</strong> retornada pela consulta e também está realizando um <strong>JOIN FETCH</strong>(JUNTAR BUSCAR) com a propriedade <strong>categoryProduct</strong>, isso indica que a consulta deve carregar os dados da categoria associada a cada produto na mesma consulta em vez de realizar consultas adicionais para carregar as categorias separadamente, isso reflete no resultado do JSON fazendo com que a categoria venha pendurada no produto.<br>
A anotação está encima do método <strong>searchAll()</strong> que é moldado por uma lista que aceita objetos do tipo <strong>Product</strong>, ou seja, quando o método <strong>searchAll()</strong> for chamado a consulta dentro da query será realizada na lista de produtos.
</p>

```java
@Query(value = "SELECT obj FROM Product obj JOIN FETCH obj.categoryProduct")
List<Product> searchAll();
}
```

#### CategoryRepository

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.Category;
```

<p>
Depois criamos a interface <strong>CategoryRepository</strong> que se extende a interface <strong>JpaRepository</strong>(simplifica o desenvolvimento de operações de acesso a dados em BD's relacionais usando JPA, interface útil para operações CRUD padrão e consultas dinâmicas) que recebe como parâmetro as entidades <strong>Category</strong> e <strong>Long</strong>, ou seja, irá aceitar objetos desse tipo.
</p>

```java
public interface CategoryRepository extends JpaRepository<Category, Long> {
```

<p>
Dentro da interface inserimos a anotação <strong>@Query</strong> que serve para indicar a criação de uma query que recebe como valor uma consulta JPQL(consulta orientada a objetos), a consulta está solicitando todas as instâncias da entidade <strong>Category</strong>, o alias <strong>obj</strong> é usado para se referir a cada instância de <strong>Category</strong> retornada pela consulta.<br>
A anotação está encima do método <strong>searchAll()</strong> que é moldado por uma lista que aceita objetos do tipo <strong>Category</strong>, ou seja, quando o método <strong>searchAll()</strong> for chamado será feita uma consulta na lista de categorias para trazer todas as instâncias da entidade <strong>Category</strong>
</p>

```java
@Query(value = "SELECT obj FROM Category obj")
List<Category>searchAll(); 
}
```

#### OrderRepository

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.Order;
```

<p>
Depois criamos a interface <strong>OrderRepository</strong> que se extende a interface <strong>JpaRepository</strong>(simplifica o desenvolvimento de operações de acesso a dados em BD's relacionais usando JPA, interface útil para operações CRUD padrão e consultas dinâmicas) que recebe como parâmetro as entidades <strong>Order</strong> e <strong>Long</strong>, ou seja, irá aceitar objetos desse tipo.
</p>

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
```

<p>
Dentro da interface inserimos a anotação <strong>@Query</strong> que serve para indicar a criação de uma query que recebe como valor uma consulta JPQL(consulta orientada a objetos), a consulta está solicitando todas as instâncias da entidade <strong>Order</strong>, o alias <strong>obj</strong> é usado para se referir a cada instância de <strong>Order</strong> retornada pela consulta e também está realizando um <strong>JOIN FETCH</strong>(JUNTAR BUSCAR) com a propriedade <strong>client</strong>, isso indica que a consulta deve carregar os dados do cliente associado a cada pedido na mesma consulta em vez de realizar consultas adicionais para carregar os clientes separadamente, isso reflete no resultado do JSON fazendo com que o cliente venha pendurado no pedido.<br>
A anotação está encima do método <strong>searchAll()</strong> que é moldado por uma lista que aceita objetos do tipo <strong>Order</strong>, ou seja, quando o método <strong>searchAll()</strong> for chamado a consulta dentro da query será realizada na lista de pedidos.
</p>

```java
@Query(value = "SELECT obj FROM Order obj JOIN FETCH obj.client")
List<Order>searchAll();
}
```

#### UserRepository

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.User;
```

<p>
Depois criamos a interface <strong>UserRepository</strong> que se extende a interface <strong>JpaRepository</strong>(simplifica o desenvolvimento de operações de acesso a dados em BD's relacionais usando JPA, interface útil para operações CRUD padrão e consultas dinâmicas) que recebe como parâmetro as entidades <strong>User</strong> e <strong>Long</strong>, ou seja, irá aceitar objetos desse tipo.
</p>

```java
public interface UserRepository extends JpaRepository<User, Long> {
```

<p>
Dentro da interface inserimos a anotação <strong>@Query</strong> que serve para indicar a criação de uma query que recebe como valor uma consulta JPQL(consulta orientada a objetos), a consulta está solicitando todas as instâncias da entidade <strong>User</strong>, o alias <strong>obj</strong> é usado para se referir a cada instância de <strong>User</strong> retornada pela consulta.<br>
A anotação está encima do método <strong>searchAll()</strong> que é moldado por uma lista que aceita objetos do tipo <strong>User</strong>, ou seja, quando o método <strong>searchAll()</strong> for chamado será feita uma consulta na lista de categorias para trazer todas as instâncias da entidade <strong>User</strong>
</p>

```java
@Query(value = "SELECT obj FROM User obj")
List<User>searchAll();
}
```

#### OrderItemRepository

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.dto.InterfaceOrderItem;
import com.requests.project.entities.OrderItem;
```

<p>
Depois criamos a interface <strong>OrderItemRepository</strong> que se extende a interface <strong>JpaRepository</strong>(simplifica o desenvolvimento de operações de acesso a dados em BD's relacionais usando JPA, interface útil para operações CRUD padrão e consultas dinâmicas) que recebe como parâmetro as entidades <strong>OrderItem</strong> e <strong>Long</strong>, ou seja, irá aceitar objetos desse tipo.
</p>

```java
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
```

<p>
Dentro da interface inserimos a anotação <strong>@Query</strong> que serve para indicar a criação de uma query que recebe como valor uma consulta SQL nativa(consulta SQL comum interpretada pelo BD), a consulta está solicitando todos os dados da tabela <strong>tb_order_item</strong> e também a soma do valor total do item do pedido.<br>
A anotação está encima do método <strong>procurarAll()</strong> que é moldado por uma lista que aceita objetos do tipo <strong>InterfaceOrderItem</strong>, ou seja, quando o método <strong>procurarAll()</strong> for chamado a consulta dentro da query será realizada na lista que contém a interface <strong>InterfaceOrderItem</strong> e dentro dessa interface existem métodos que são responsáveis por fazer <strong>get</strong>(pegar dados).
</p>

```java
@Query(value = "SELECT id AS id,"
             + "(price * quantity) AS orderItemTotal,"
             + "price AS productPrice,"
             + " quantity AS orderItemQuantity,"
             + " order_id AS orderId,"
             + " product_id AS productId"
             + " FROM tb_order_item"
             , nativeQuery = true)
List<InterfaceOrderItem> procurarAll();
}
```

#### InterfaceOrderItem

<p>
Diferente de uma query JPQL(consulta orientada a objetos), uma query SQL(consulta nativa de um BD) depende de uma interface para funcionar, isso quando estamos falando da criação de uma JPA(Java Persistence API). Quando temos uma consulta SQL nativa dentro de um repositório cada linha da consulta é executada através da chamada de um método, por isso criamos a interface <strong>InterfaceOrderItem</strong> que armazena os métodos <strong>get</strong> que pegam dados, vamos fazer o código, primeiro definimos o pacote que a interface irá pertencer.
</p>

```java
package com.requests.project.dto;
```

<p>
Depois criamos a interface com os métodos.
</p>

```java
public interface InterfaceOrderItem {
    Long getId();
    Double getProductPrice();
    Integer getOrderItemQuantity();
    Long getOrderId();
    Long getProductId();
    Double getOrderItemTotal();
}
```

<p>
Sabemos que cada linha da consulta SQL nativa dentro do repositório <strong>OrderItemRepository</strong> irá chamar um método da interface <strong>InterfaceOrderItem</strong>, segue a explicação para melhor entendimento:
</p>

```java
"SELECT id AS id," /*CHAMA O MÉTODO*/ getId();
"(price * quantity) AS orderItemTotal," /*CHAMA O MÉTODO*/ getOrderItemTotal();
"price AS productPrice," /*CHAMA O MÉTODO*/ getProductPrice();
"quantity AS orderItemQuantity," /*CHAMA O MÉTODO*/ getOrderItemQuantity();
"order_id AS orderId," /*CHAMA O MÉTODO*/ getOrderId();
"product_id AS productId" /*CHAMA O MÉTODO*/ getProductId();
```

### Criando Controladores

<p>
Essas classes servem para gerenciar as requisições HTTP, receber os dados das requisições feitas pelo usuário, chamar os serviços necessários e retornar as respostas apropriadas.
</p>

#### ProductResource

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.requests.project.entities.Product;
import com.requests.project.services.ProductService;
```

<p>
Criamos a classe <strong>ProductResource</strong> e inserimos duas anotações encima da classe, <strong>@RestController</strong> que serve para indicar que a classe é um controlador específico para APIs RESTful onde cada método do controlador retorna diretamente dados serializados no corpo da resposta HTTP e <strong>@RequestMapping(value = "/products")</strong> que é usado para mapear um determinado caminho (path) à classe e a métodos específicos dentro da classe controladora, ou seja,  <strong>"/products"</strong> faz parte da estrutura da URL que o usuário deve acessar para fazer requisições CRUD's, este é o endpoint específico para fazer requisições referentes aos produtos.
</p>

```java
@RestController
@RequestMapping(value = "/products")
public class ProductResource {
```

<p>
A variável <strong>service</strong> é moldada pela classe <strong>ProductService</strong>, inserimos a anotação <strong>@Autowired</strong> que realiza a injeção de dependências para a variável, o Spring fica responsável pela criação e gestão da instância da dependência <strong>service</strong>.
</p>

```java
@Autowired
private ProductService service;
```

<p>
Criamos um método e inserimos a anotação <strong>@GetMapping</strong> encima do método, essa anotação é usada para mapear solicitações HTTP GET para métodos de manipulação em controladores. 
O método <strong>findAll()</strong> é moldado pela classe <strong>ResponseEntity</strong> que aceita uma <strong>List</strong> que aceita objetos do tipo <strong>Product</strong>, a classe <strong>ResponseEntity</strong> é uma classe pronta do Spring Framework que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP. Dentro do método <strong>findAll()</strong> existe a variável <strong>list</strong> que é moldada por uma <strong>List</strong> que aceita objetos do tipo <strong>Product</strong>, a variável <strong>list</strong> recebe a variável <strong>service</strong> que chama o método <strong>searchAll()</strong> que está dentro da classe <strong>ProductService</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(list)</strong> é chamado para especificar o corpo da resposta, que é a lista de produtos <strong>(list)</strong>.
Este método é responsável por pegar todos os produtos.
</p>

```java
@GetMapping
public ResponseEntity<List<Product>> findAll() {
	List<Product> list = service.searchAll();
	return ResponseEntity.ok().body(list);
}
```	

<p>
Depois criamos um método quase igual ao anterior, a diferença é que ao invês de pegar todos os produtos iremos pegar um único produto através do seu <strong>id</strong>, por isso o método <strong>@GetMapping</strong> agora tem um valor como parâmetro, <strong>(value = "/{id}")</strong>, esse <strong>id</strong> quem passa é o usuário que faz a requisição GET, repare também que o nome do método muda para <strong>findById</strong> e é passada a variável <strong>id</strong> como argumento, essa variável é moldada pela classe <strong>Long</strong> que no caso é a tipagem do argumento e também contém a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL. Repare também que	a variável <strong>service</strong> chama outro método que está na classe <strong>CategoryService</strong>, passando <strong>id</strong> como argumento, observe: <strong>findById(id)</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(obj)</strong> é chamado para especificar o corpo da resposta, que é o objeto, no caso o produto.
</p>

```java
@GetMapping(value = "/{id}")
public ResponseEntity<Product> findById(@PathVariable Long id) {
	Product obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
}
```	

<p>
Também criamos o método responsável por inserir(POST), começamos colocando a anotação <strong>@PostMapping</strong> que no Spring é usada para mapear solicitações HTTP POST para métodos de manipulação em controladores. Criamos o método <strong>insert</strong> que é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>Product</strong> e passa como argumento a variável <strong>obj</strong> que é moldada pela classe <strong>Product</strong> e também tem a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o produto.
</p>

```java
@PostMapping
public ResponseEntity<Product> insert(@RequestBody Product obj) {
```
<p>
A variável <strong>obj</strong> recebe o serviço de inserção que é um método(está dentro da classe <strong>ProductService</strong>) e o argumento passado para esse método é o <strong>obj</strong> que no caso é o objeto que foi passado no argumento do <strong>insert</strong>, este objeto é o corpo do produto que deve ser inserido.
</p>

```java
obj = service.insert(obj);
```

<p>
A linha a seguir está construindo uma <strong>URI</strong> para o novo recurso inserido. Essa <strong>URI</strong> inclui o caminho atual da requisição, adiciona <strong>"/{id}"</strong> como parte do caminho, e substitui <strong>{id}</strong> pelo ID do objeto <strong>Product</strong> recém-inserido. O objetivo é criar uma <strong>URI</strong> que aponte para o novo recurso, facilitando a localização e acesso a ele. Essa <strong>URI</strong> pode ser incluída no cabeçalho da resposta HTTP para indicar onde o recurso recém-criado está disponível.
</p>

```java
URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
```

<p>
A linha de código abaixo está criando e retornando uma resposta HTTP com status <strong>201 (Created)</strong> indicando que a operação de criação foi bem-sucedida. A <strong>URI</strong> do novo recurso é incluída no cabeçalho da resposta, e o corpo(<strong>body</strong>) da resposta contém o objeto <strong>Product</strong> recém-criado.
</p>

```java
return ResponseEntity.created(uri).body(obj);
}
```

<p>
Também criamos o método responsável por deletar(DELETE), começamos inserindo a anotação <strong>@DeleteMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP DELETE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição DELETE o usuário deverá informar o <strong>id</strong> do produto que quer deletar. O método <strong>delete</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status). que aceita objetos do tipo <strong>Void</strong>(vazio) e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é <strong>id</strong> do produto que será deletado(o usuário deverá passar esse valor no momento da requisição).
</p>

```java
@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
}
```

<p>
Variável <strong>service</strong> chama o método <strong>delete</strong> e passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do produto que será deletado.
</p>

```java
service.delete(id);
```

<p>
o <strong>return</strong> abaixo  está construindo e retornando uma resposta HTTP com status 204 No Content, em resumo, a linha mencionada está indicando que a operação de exclusão foi bem-sucedida.
</p>

```java
return ResponseEntity.noContent().build();
}
```

<p>
Também criamos o método responsável por atualizar(PUT), começamos inserindo a anotação <strong>@PutMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP UPDATE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição UPDATE o usuário deverá informar o <strong>id</strong> do produto que quer atualizar. O método <strong>update</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>Product</strong> e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é o <strong>id</strong> do produto que será atualizado(o usuário deverá passar esse valor no momento da requisição), já o outro argumento <strong>obj</strong> irá receber os dados da atualização, a variável <strong>obj</strong> é moldada pela classe <strong>Product</strong> e contém a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o produto.
</p>

```java
@PutMapping(value = "/{id}")
public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj) {
```

<p>
A variável <strong>obj</strong> recebe a variável <strong>service</strong> que chama o método <strong>update</strong> da classe <strong>ProductService</strong> e passamos os argumentos <strong>id</strong> e <strong>obj</strong>, onde <strong>id</strong> será o id do produto que será atualizado e <strong>obj</strong> será os dados que serão passados para a atualização.
</p>

```java
obj = service.update(id, obj);
```

<p>
o <strong>return</strong> abaixo está retornando um objeto do tipo ResponseEntity, que é uma classe do Spring usada para representar toda a resposta HTTP. <strong>ResponseEntity.ok()</strong> retorna um status HTTP 200 OK. O método <strong>body(obj)</strong> define o corpo da resposta como o objeto <strong>Product</strong> atualizado.
</p>

```java
return ResponseEntity.ok().body(obj);
}
```
```java
}
```

#### CategoryResource

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.requests.project.entities.Category;
import com.requests.project.services.CategoryService;
```

<p>
Criamos a classe <strong>CategoryResource</strong> e inserimos duas anotações encima da classe, <strong>@RestController</strong> que serve para indicar que a classe é um controlador específico para APIs RESTful onde cada método do controlador retorna diretamente dados serializados no corpo da resposta HTTP e <strong>@RequestMapping(value = "/categories")</strong> que é usado para mapear um determinado caminho (path) à classe e a métodos específicos dentro da classe controladora, ou seja,  <strong>"/categories"</strong> faz parte da estrutura da URL que o usuário deve acessar para fazer requisições CRUD's, este é o endpoint específico para fazer requisições referentes as categorias.
</p>

```java
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
```

<p>
A variável <strong>service</strong> é moldada pela classe <strong>CategoryService</strong>, inserimos a anotação <strong>@Autowired</strong> que realiza a injeção de dependências para a variável, o Spring fica responsável pela criação e gestão da instância da dependência <strong>service</strong>.
</p>

```java
@Autowired
private CategoryService service;
```

<p>
Criamos um método e inserimos a anotação <strong>@GetMapping</strong> encima do método, essa anotação é usada para mapear solicitações HTTP GET para métodos de manipulação em controladores. 
O método <strong>findAll()</strong> é moldado pela classe <strong>ResponseEntity</strong> que aceita uma <strong>List</strong> que aceita objetos do tipo <strong>Category</strong>, a classe <strong>ResponseEntity</strong> é uma classe pronta do Spring Framework que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP. Dentro do método <strong>findAll()</strong> existe a variável <strong>list</strong> que é moldada por uma <strong>List</strong> que aceita objetos do tipo <strong>Category</strong>, a variável <strong>list</strong> recebe a variável <strong>service</strong> que chama o método <strong>searchAll()</strong> que está dentro da classe <strong>CategoryService</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(list)</strong> é chamado para especificar o corpo da resposta, que é a lista de categorias <strong>(list)</strong>.
Este método é responsável por pegar todos as categorias.
</p>

```java
@GetMapping
public ResponseEntity<List<Category>> findAll() {
	List<Category> list = service.searchAll();
	return ResponseEntity.ok().body(list);
}
```	

<p>
Depois criamos um método quase igual ao anterior, a diferença é que ao invês de pegar todos as categorias iremos pegar a única categoria através do seu <strong>id</strong>, por isso o método <strong>@GetMapping</strong> agora tem um valor como parâmetro, <strong>(value = "/{id}")</strong>, esse <strong>id</strong> quem passa é o usuário que faz a requisição GET, repare também que o nome do método muda para <strong>findById</strong> e é passada a variável <strong>id</strong> como argumento, essa variável é moldada pela classe <strong>Long</strong> que no caso é a tipagem do argumento e também contém a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL. Repare também que	a variável <strong>service</strong> chama outro método que está na classe <strong>CategoryService</strong>, passando <strong>id</strong> como argumento, observe: <strong>findById(id)</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(obj)</strong> é chamado para especificar o corpo da resposta, que é o objeto, no caso o item do pedido.
</p>

```java
@GetMapping(value = "/{id}")
public ResponseEntity<Category> findById(@PathVariable Long id) {
	Category obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
}
```	

<p>
Também criamos o método responsável por inserir(POST), começamos colocando a anotação <strong>@PostMapping</strong> que no Spring é usada para mapear solicitações HTTP POST para métodos de manipulação em controladores. Criamos o método <strong>insert</strong> que é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>Category</strong> e passa como argumento a variável <strong>obj</strong> que é moldada pela classe <strong>Category</strong> e também tem a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é a categoria.
</p>

```java
@PostMapping
public ResponseEntity<Category> insert(@RequestBody Category obj) {
```
<p>
A variável <strong>obj</strong> recebe o serviço de inserção que é um método(está dentro da classe <strong>CategoryService</strong>) e o argumento passado para esse método é o <strong>obj</strong> que no caso é o objeto que foi passado no argumento do <strong>insert</strong>, este objeto é o corpo da categoria que deve ser inserido.
</p>

```java
obj = service.insert(obj);
```

<p>
A linha a seguir está construindo uma <strong>URI</strong> para o novo recurso inserido. Essa <strong>URI</strong> inclui o caminho atual da requisição, adiciona <strong>"/{id}"</strong> como parte do caminho, e substitui <strong>{id}</strong> pelo ID do objeto <strong>Category</strong> recém-inserido. O objetivo é criar uma <strong>URI</strong> que aponte para o novo recurso, facilitando a localização e acesso a ele. Essa <strong>URI</strong> pode ser incluída no cabeçalho da resposta HTTP para indicar onde o recurso recém-criado está disponível.
</p>

```java
URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
```

<p>
A linha de código abaixo está criando e retornando uma resposta HTTP com status <strong>201 (Created)</strong> indicando que a operação de criação foi bem-sucedida. A <strong>URI</strong> do novo recurso é incluída no cabeçalho da resposta, e o corpo(<strong>body</strong>) da resposta contém o objeto <strong>Category</strong> recém-criado.
</p>

```java
return ResponseEntity.created(uri).body(obj);
}
```

<p>
Também criamos o método responsável por deletar(DELETE), começamos inserindo a anotação <strong>@DeleteMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP DELETE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição DELETE o usuário deverá informar o <strong>id</strong> do produto que quer deletar. O método <strong>delete</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status). que aceita objetos do tipo <strong>Void</strong>(vazio) e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é <strong>id</strong> da categoria que será deletado(o usuário deverá passar esse valor no momento da requisição).
</p>

```java
@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
}
```

<p>
Variável <strong>service</strong> chama o método <strong>delete</strong> e passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> da categoria que será deletada.
</p>

```java
service.delete(id);
```

<p>
o <strong>return</strong> abaixo  está construindo e retornando uma resposta HTTP com status 204 No Content, em resumo, a linha mencionada está indicando que a operação de exclusão foi bem-sucedida.
</p>

```java
return ResponseEntity.noContent().build();
}
```

<p>
Também criamos o método responsável por atualizar(PUT), começamos inserindo a anotação <strong>@PutMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP UPDATE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição UPDATE o usuário deverá informar o <strong>id</strong> da categoria que quer atualizar. O método <strong>update</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>Category</strong> e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é o <strong>id</strong> da categoria que será atualizado(o usuário deverá passar esse valor no momento da requisição), já o outro argumento <strong>obj</strong> irá receber os dados da atualização, a variável <strong>obj</strong> é moldada pela classe <strong>Category</strong> e contém a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é a categoria.
</p>

```java
@PutMapping(value = "/{id}")
public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj) {
```

<p>
A variável <strong>obj</strong> recebe a variável <strong>service</strong> que chama o método <strong>update</strong> da classe <strong>CategoryService</strong> e passamos os argumentos <strong>id</strong> e <strong>obj</strong>, onde <strong>id</strong> será o id da categoria que será atualizada e <strong>obj</strong> será os dados que serão passados para a atualização.
</p>

```java
obj = service.update(id, obj);
```

<p>
o <strong>return</strong> abaixo está retornando um objeto do tipo ResponseEntity, que é uma classe do Spring usada para representar toda a resposta HTTP. <strong>ResponseEntity.ok()</strong> retorna um status HTTP 200 OK. O método <strong>body(obj)</strong> define o corpo da resposta como o objeto <strong>Category</strong> atualizado.
</p>

```java
return ResponseEntity.ok().body(obj);
}
```
```java
}
```

#### OrderResource

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.requests.project.entities.Order;
import com.requests.project.services.OrderService;
```

<p>
Criamos a classe <strong>OrderResource</strong> e inserimos duas anotações encima da classe, <strong>@RestController</strong> que serve para indicar que a classe é um controlador específico para APIs RESTful onde cada método do controlador retorna diretamente dados serializados no corpo da resposta HTTP e <strong>@RequestMapping(value = "/orders")</strong> que é usado para mapear um determinado caminho (path) à classe e a métodos específicos dentro da classe controladora, ou seja,  <strong>"/orders"</strong> faz parte da estrutura da URL que o usuário deve acessar para fazer requisições CRUD's, este é o endpoint específico para fazer requisições referentes aos pedidos.
</p>

```java
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
```

<p>
A variável <strong>service</strong> é moldada pela classe <strong>OrderService</strong>, inserimos a anotação <strong>@Autowired</strong> que realiza a injeção de dependências para a variável, o Spring fica responsável pela criação e gestão da instância da dependência <strong>service</strong>.
</p>

```java
@Autowired
private OrderService service;
```

<p>
Criamos um método e inserimos a anotação <strong>@GetMapping</strong> encima do método, essa anotação é usada para mapear solicitações HTTP GET para métodos de manipulação em controladores. 
O método <strong>findAll()</strong> é moldado pela classe <strong>ResponseEntity</strong> que aceita uma <strong>List</strong> que aceita objetos do tipo <strong>Order</strong>, a classe <strong>ResponseEntity</strong> é uma classe pronta do Spring Framework que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP. Dentro do método <strong>findAll()</strong> existe a variável <strong>list</strong> que é moldada por uma <strong>List</strong> que aceita objetos do tipo <strong>Order</strong>, a variável <strong>list</strong> recebe a variável <strong>service</strong> que chama o método <strong>searchAll()</strong> que está dentro da classe <strong>OrderService</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(list)</strong> é chamado para especificar o corpo da resposta, que é a lista de pedidos <strong>(list)</strong>.
Este método é responsável por pegar todos as pedidos.
</p>

```java
@GetMapping
public ResponseEntity<List<Order>> findAll() {
	List<Order> list = service.searchAll();
	return ResponseEntity.ok().body(list);
}
```	

<p>
Depois criamos um método quase igual ao anterior, a diferença é que ao invês de pegar todos as pedidos iremos pegar a única categoria através do seu <strong>id</strong>, por isso o método <strong>@GetMapping</strong> agora tem um valor como parâmetro, <strong>(value = "/{id}")</strong>, esse <strong>id</strong> quem passa é o usuário que faz a requisição GET, repare também que o nome do método muda para <strong>findById</strong> e é passada a variável <strong>id</strong> como argumento, essa variável é moldada pela classe <strong>Long</strong> que no caso é a tipagem do argumento e também contém a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL. Repare também que	a variável <strong>service</strong> chama outro método que está na classe <strong>OrderService</strong>, passando <strong>id</strong> como argumento, observe: <strong>findById(id)</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(obj)</strong> é chamado para especificar o corpo da resposta, que é o objeto, no caso o pedido.
</p>

```java
@GetMapping(value = "/{id}")
public ResponseEntity<Order> findById(@PathVariable Long id) {
	Order obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
}
```	

<p>
Também criamos o método responsável por inserir(POST), começamos colocando a anotação <strong>@PostMapping</strong> que no Spring é usada para mapear solicitações HTTP POST para métodos de manipulação em controladores. Criamos o método <strong>insert</strong> que é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>Order</strong> e passa como argumento a variável <strong>obj</strong> que é moldada pela classe <strong>Order</strong> e também tem a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o pedido.
</p>

```java
@PostMapping
public ResponseEntity<Order> insert(@RequestBody Order obj) {
```
<p>
A variável <strong>obj</strong> recebe o serviço de inserção que é um método(está dentro da classe <strong>OrderService</strong>) e o argumento passado para esse método é o <strong>obj</strong> que no caso é o objeto que foi passado no argumento do <strong>insert</strong>, este objeto é o corpo do pedido que deve ser inserido.
</p>

```java
obj = service.insert(obj);
```

<p>
A linha a seguir está construindo uma <strong>URI</strong> para o novo recurso inserido. Essa <strong>URI</strong> inclui o caminho atual da requisição, adiciona <strong>"/{id}"</strong> como parte do caminho, e substitui <strong>{id}</strong> pelo ID do objeto <strong>Order</strong> recém-inserido. O objetivo é criar uma <strong>URI</strong> que aponte para o novo recurso, facilitando a localização e acesso a ele. Essa <strong>URI</strong> pode ser incluída no cabeçalho da resposta HTTP para indicar onde o recurso recém-criado está disponível.
</p>

```java
URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
```

<p>
A linha de código abaixo está criando e retornando uma resposta HTTP com status <strong>201 (Created)</strong> indicando que a operação de criação foi bem-sucedida. A <strong>URI</strong> do novo recurso é incluída no cabeçalho da resposta, e o corpo(<strong>body</strong>) da resposta contém o objeto <strong>Order</strong> recém-criado.
</p>

```java
return ResponseEntity.created(uri).body(obj);
}
```

<p>
Também criamos o método responsável por deletar(DELETE), começamos inserindo a anotação <strong>@DeleteMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP DELETE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição DELETE o usuário deverá informar o <strong>id</strong> do pedido que quer deletar. O método <strong>delete</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status). que aceita objetos do tipo <strong>Void</strong>(vazio) e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é <strong>id</strong> do pedido que será deletado(o usuário deverá passar esse valor no momento da requisição).
</p>

```java
@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
}
```

<p>
Variável <strong>service</strong> chama o método <strong>delete</strong> e passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do pedido que será deletado.
</p>

```java
service.delete(id);
```

<p>
O <strong>return</strong> abaixo  está construindo e retornando uma resposta HTTP com status 204 No Content, em resumo, a linha mencionada está indicando que a operação de exclusão foi bem-sucedida.
</p>

```java
return ResponseEntity.noContent().build();
}
```

<p>
Também criamos o método responsável por atualizar(PUT), começamos inserindo a anotação <strong>@PutMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP UPDATE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição UPDATE o usuário deverá informar o <strong>id</strong> do pedido que quer atualizar. O método <strong>update</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>Order</strong> e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é o <strong>id</strong> do pedido que será atualizado(o usuário deverá passar esse valor no momento da requisição), já o outro argumento <strong>obj</strong> irá receber os dados da atualização, a variável <strong>obj</strong> é moldada pela classe <strong>Order</strong> e contém a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o pedido.
</p>

```java
@PutMapping(value = "/{id}")
public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj) {
```

<p>
A variável <strong>obj</strong> recebe a variável <strong>service</strong> que chama o método <strong>update</strong> da classe <strong>OrderService</strong> e passamos os argumentos <strong>id</strong> e <strong>obj</strong>, onde <strong>id</strong> será o id do pedido que será atualizado e <strong>obj</strong> será os dados que serão passados para a atualização.
</p>

```java
obj = service.update(id, obj);
```

<p>
o <strong>return</strong> abaixo está retornando um objeto do tipo ResponseEntity, que é uma classe do Spring usada para representar toda a resposta HTTP. <strong>ResponseEntity.ok()</strong> retorna um status HTTP 200 OK. O método <strong>body(obj)</strong> define o corpo da resposta como o objeto <strong>Order</strong> atualizado.
</p>

```java
return ResponseEntity.ok().body(obj);
}
```
```java
}
```

#### UserResource

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.requests.project.entities.User;
import com.requests.project.services.UserService;
```

<p>
Criamos a classe <strong>UserResource</strong> e inserimos duas anotações encima da classe, <strong>@RestController</strong> que serve para indicar que a classe é um controlador específico para APIs RESTful onde cada método do controlador retorna diretamente dados serializados no corpo da resposta HTTP e <strong>@RequestMapping(value = "/users")</strong> que é usado para mapear um determinado caminho (path) à classe e a métodos específicos dentro da classe controladora, ou seja, <strong>"/users"</strong> faz parte da estrutura da URL que o usuário deve acessar para fazer requisições CRUD's, este é o endpoint específico para fazer requisições referentes aos usuários.
</p>

```java
@RestController
@RequestMapping(value = "/users")
public class UserResource {
```

<p>
A variável <strong>service</strong> é moldada pela classe <strong>UserService</strong>, inserimos a anotação <strong>@Autowired</strong> que realiza a injeção de dependências para a variável, o Spring fica responsável pela criação e gestão da instância da dependência <strong>service</strong>.
</p>

```java
@Autowired
private UserService service;
```

<p>
Criamos um método e inserimos a anotação <strong>@GetMapping</strong> encima do método, essa anotação é usada para mapear solicitações HTTP GET para métodos de manipulação em controladores. 
O método <strong>findAll()</strong> é moldado pela classe <strong>ResponseEntity</strong> que aceita uma <strong>List</strong> que aceita objetos do tipo <strong>User</strong>, a classe <strong>ResponseEntity</strong> é uma classe pronta do Spring Framework que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP. Dentro do método <strong>findAll()</strong> existe a variável <strong>list</strong> que é moldada por uma <strong>List</strong> que aceita objetos do tipo <strong>User</strong>, a variável <strong>list</strong> recebe a variável <strong>service</strong> que chama o método <strong>searchAll()</strong> que está dentro da classe <strong>UserService</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(list)</strong> é chamado para especificar o corpo da resposta, que é a lista de usuários <strong>(list)</strong>.
Este método é responsável por pegar todos os usuários.
</p>

```java
@GetMapping
public ResponseEntity<List<User>> findAll() {
	List<User> list = service.searchAll();
	return ResponseEntity.ok().body(list);
}
```	

<p>
Depois criamos um método quase igual ao anterior, a diferença é que ao invês de pegar todos os usuários iremos pegar um único usuário através do seu <strong>id</strong>, por isso o método <strong>@GetMapping</strong> agora tem um valor como parâmetro, <strong>(value = "/{id}")</strong>, esse <strong>id</strong> quem passa é o usuário que faz a requisição GET, repare também que o nome do método muda para <strong>findById</strong> e é passada a variável <strong>id</strong> como argumento, essa variável é moldada pela classe <strong>Long</strong> que no caso é a tipagem do argumento e também contém a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL. Repare também que	a variável <strong>service</strong> chama outro método que está na classe <strong>UserService</strong>, passando <strong>id</strong> como argumento, observe: <strong>findById(id)</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(obj)</strong> é chamado para especificar o corpo da resposta, que é o objeto, no caso o usuário.
</p>

```java
@GetMapping(value = "/{id}")
public ResponseEntity<User> findById(@PathVariable Long id) {
	User obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
}
```	

<p>
Também criamos o método responsável por inserir(POST), começamos colocando a anotação <strong>@PostMapping</strong> que no Spring é usada para mapear solicitações HTTP POST para métodos de manipulação em controladores. Criamos o método <strong>insert</strong> que é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>User</strong> e passa como argumento a variável <strong>obj</strong> que é moldada pela classe <strong>User</strong> e também tem a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o usuário.
</p>

```java
@PostMapping
public ResponseEntity<User> insert(@RequestBody User obj) {
```
<p>
A variável <strong>obj</strong> recebe o serviço de inserção que é um método(está dentro da classe <strong>UserService</strong>) e o argumento passado para esse método é o <strong>obj</strong> que no caso é o objeto que foi passado no argumento do <strong>insert</strong>, este objeto é o corpo do usuário que deve ser inserido.
</p>

```java
obj = service.insert(obj);
```

<p>
A linha a seguir está construindo uma <strong>URI</strong> para o novo recurso inserido. Essa <strong>URI</strong> inclui o caminho atual da requisição, adiciona <strong>"/{id}"</strong> como parte do caminho, e substitui <strong>{id}</strong> pelo ID do objeto <strong>User</strong> recém-inserido. O objetivo é criar uma <strong>URI</strong> que aponte para o novo recurso, facilitando a localização e acesso a ele. Essa <strong>URI</strong> pode ser incluída no cabeçalho da resposta HTTP para indicar onde o recurso recém-criado está disponível.
</p>

```java
URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
```

<p>
A linha de código abaixo está criando e retornando uma resposta HTTP com status <strong>201 (Created)</strong> indicando que a operação de criação foi bem-sucedida. A <strong>URI</strong> do novo recurso é incluída no cabeçalho da resposta, e o corpo(<strong>body</strong>) da resposta contém o objeto <strong>User</strong> recém-criado.
</p>

```java
return ResponseEntity.created(uri).body(obj);
}
```

<p>
Também criamos o método responsável por deletar(DELETE), começamos inserindo a anotação <strong>@DeleteMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP DELETE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição DELETE o usuário deverá informar o <strong>id</strong> do usuário que quer deletar. O método <strong>delete</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status). que aceita objetos do tipo <strong>Void</strong>(vazio) e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é <strong>id</strong> do usuário que será deletado(o usuário deverá passar esse valor no momento da requisição).
</p>

```java
@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
}
```

<p>
Variável <strong>service</strong> chama o método <strong>delete</strong> e passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do usuário que será deletado.
</p>

```java
service.delete(id);
```

<p>
o <strong>return</strong> abaixo  está construindo e retornando uma resposta HTTP com status 204 No Content, em resumo, a linha mencionada está indicando que a operação de exclusão foi bem-sucedida.
</p>

```java
return ResponseEntity.noContent().build();
}
```

<p>
Também criamos o método responsável por atualizar(PUT), começamos inserindo a anotação <strong>@PutMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP UPDATE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição UPDATE o usuário deverá informar o <strong>id</strong> do usuário que quer atualizar. O método <strong>update</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>User</strong> e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é o <strong>id</strong> do usuário que será atualizado(o usuário deverá passar esse valor no momento da requisição), já o outro argumento <strong>obj</strong> irá receber os dados da atualização, a variável <strong>obj</strong> é moldada pela classe <strong>User</strong> e contém a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o usuário.
</p>

```java
@PutMapping(value = "/{id}")
public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
```

<p>
A variável <strong>obj</strong> recebe a variável <strong>service</strong> que chama o método <strong>update</strong> da classe <strong>UserService</strong> e passamos os argumentos <strong>id</strong> e <strong>obj</strong>, onde <strong>id</strong> será o id do usuário que será atualizado e <strong>obj</strong> será os dados que serão passados para a atualização.
</p>

```java
obj = service.update(id, obj);
```

<p>
o <strong>return</strong> abaixo está retornando um objeto do tipo ResponseEntity, que é uma classe do Spring usada para representar toda a resposta HTTP. <strong>ResponseEntity.ok()</strong> retorna um status HTTP 200 OK. O método <strong>body(obj)</strong> define o corpo da resposta como o objeto <strong>User</strong> atualizado.
</p>

```java
return ResponseEntity.ok().body(obj);
}
```
```java
}
```

#### OrderItemResource

<p>
Primeiro definimos o pacote que a classe irá pertencer e depois fazemos as importações necessárias.
</p>

```java
package com.requests.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.requests.project.dto.InterfaceOrderItem;
import com.requests.project.entities.OrderItem;
import com.requests.project.services.OrderItemService;
```

<p>
Criamos a classe <strong>OrderItemResource</strong> e inserimos duas anotações encima da classe, <strong>@RestController</strong> que serve para indicar que a classe é um controlador específico para APIs RESTful onde cada método do controlador retorna diretamente dados serializados no corpo da resposta HTTP e <strong>@RequestMapping(value = "/orderitem")</strong> que é usado para mapear um determinado caminho (path) à classe e a métodos específicos dentro da classe controladora, ou seja, <strong>"/orderitem"</strong> faz parte da estrutura da URL que o item do pedido deve acessar para fazer requisições CRUD's, este é o endpoint específico para fazer requisições referentes aos itens dos pedidos.
</p>

```java
@RestController
@RequestMapping(value = "/orderitem")
public class OrderItemResource {
```

<p>
A variável <strong>service</strong> é moldada pela classe <strong>OrderItemService</strong>, inserimos a anotação <strong>@Autowired</strong> que realiza a injeção de dependências para a variável, o Spring fica responsável pela criação e gestão da instância da dependência <strong>service</strong>.
</p>

```java
@Autowired
private OrderItemService service;
```

<p>
Criamos um método e inserimos a anotação <strong>@GetMapping</strong> encima do método, essa anotação é usada para mapear solicitações HTTP GET para métodos de manipulação em controladores. 
O método <strong>findAll()</strong> é moldado pela classe <strong>ResponseEntity</strong> que aceita uma <strong>List</strong> que aceita objetos do tipo <strong>OrderItem</strong>, a classe <strong>ResponseEntity</strong> é uma classe pronta do Spring Framework que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP. Dentro do método <strong>findAll()</strong> existe a variável <strong>list</strong> que é moldada por uma <strong>List</strong> que aceita objetos do tipo <strong>OrderItem</strong>, a variável <strong>list</strong> recebe a variável <strong>service</strong> que chama o método <strong>searchAll()</strong> que está dentro da classe <strong>OrderItemService</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(list)</strong> é chamado para especificar o corpo da resposta, que é a lista de itens dos pedidos <strong>(list)</strong>.
Este método é responsável por pegar todos os item do pedidos.
</p>

```java
@GetMapping
public ResponseEntity<List<OrderItem>> findAll() {
	List<OrderItem> list = service.searchAll();
	return ResponseEntity.ok().body(list);
}
```	

<p>
Depois criamos um método quase igual ao anterior, a diferença é que ao invês de pegar todos os item do pedidos iremos pegar um único item do pedido através do seu <strong>id</strong>, por isso o método <strong>@GetMapping</strong> agora tem um valor como parâmetro, <strong>(value = "/{id}")</strong>, esse <strong>id</strong> quem passa é o usuário que faz a requisição GET, repare também que o nome do método muda para <strong>findById</strong> e é passada a variável <strong>id</strong> como argumento, essa variável é moldada pela classe <strong>Long</strong> que no caso é a tipagem do argumento e também contém a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL. Repare também que a variável <strong>service</strong> chama outro método que está na classe <strong>OrderItemService</strong>, passando <strong>id</strong> como argumento, observe: <strong>findById(id)</strong>, por fim o <strong>return</strong> vai retornar um objeto do tipo <strong>ResponseEntity</strong>(abstração que encapsula toda a informação necessária para representar uma resposta HTTP, incluindo o corpo da resposta, cabeçalhos e o status HTTP) utilizando a fábrica estática <strong>ok()</strong> que indica um status <strong>HTTP 200 (OK)</strong>, em seguida, o método <strong>body(obj)</strong> é chamado para especificar o corpo da resposta, que é o objeto, no caso o item do pedido.
</p>

```java
@GetMapping(value = "/{id}")
public ResponseEntity<OrderItem> findById(@PathVariable Long id) {
	OrderItem obj = service.findById(id);
	return ResponseEntity.ok().body(obj);
}
```	

<p>
Também criamos o método responsável por inserir(POST), começamos colocando a anotação <strong>@PostMapping</strong> que no Spring é usada para mapear solicitações HTTP POST para métodos de manipulação em controladores. Criamos o método <strong>insert</strong> que é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>OrderItem</strong> e passa como argumento a variável <strong>obj</strong> que é moldada pela classe <strong>OrderItem</strong> e também tem a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o item do pedido.
</p>

```java
@PostMapping
public ResponseEntity<OrderItem> insert(@RequestBody OrderItem obj) {
```
<p>
A variável <strong>obj</strong> recebe o serviço de inserção que é um método(está dentro da classe <strong>OrderItemService</strong>) e o argumento passado para esse método é o <strong>obj</strong> que no caso é o objeto que foi passado no argumento do <strong>insert</strong>, este objeto é o corpo do item do pedido que deve ser inserido.
</p>

```java
obj = service.insert(obj);
```

<p>
A linha a seguir está construindo uma <strong>URI</strong> para o novo recurso inserido. Essa <strong>URI</strong> inclui o caminho atual da requisição, adiciona <strong>"/{id}"</strong> como parte do caminho, e substitui <strong>{id}</strong> pelo ID do objeto <strong>OrderItem</strong> recém-inserido. O objetivo é criar uma <strong>URI</strong> que aponte para o novo recurso, facilitando a localização e acesso a ele. Essa <strong>URI</strong> pode ser incluída no cabeçalho da resposta HTTP para indicar onde o recurso recém-criado está disponível.
</p>

```java
URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
```

<p>
A linha de código abaixo está criando e retornando uma resposta HTTP com status <strong>201 (Created)</strong> indicando que a operação de criação foi bem-sucedida. A <strong>URI</strong> do novo recurso é incluída no cabeçalho da resposta, e o corpo(<strong>body</strong>) da resposta contém o objeto <strong>OrderItem</strong> recém-criado.
</p>

```java
return ResponseEntity.created(uri).body(obj);
}
```

<p>
Também criamos o método responsável por deletar(DELETE), começamos inserindo a anotação <strong>@DeleteMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP DELETE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição DELETE o item do pedido deverá informar o <strong>id</strong> do item do pedido que quer deletar. O método <strong>delete</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status). que aceita objetos do tipo <strong>Void</strong>(vazio) e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é <strong>id</strong> do item do pedido que será deletado(o item do pedido deverá passar esse valor no momento da requisição).
</p>

```java
@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
}
```

<p>
Variável <strong>service</strong> chama o método <strong>delete</strong> e passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do item do pedido que será deletado.
</p>

```java
service.delete(id);
```

<p>
o <strong>return</strong> abaixo  está construindo e retornando uma resposta HTTP com status 204 No Content, em resumo, a linha mencionada está indicando que a operação de exclusão foi bem-sucedida.
</p>

```java
return ResponseEntity.noContent().build();
}
```

<p>
Também criamos o método responsável por atualizar(PUT), começamos inserindo a anotação <strong>@PutMapping(value = "/{id}")</strong>, essa anotação é  utilizada para mapear solicitações HTTP UPDATE para um método de manipulação em um controlador, passamos <strong>/{id}</strong> como valor, ou seja, ao realizar a requisição UPDATE o item do pedido deverá informar o <strong>id</strong> do item do pedido que quer atualizar. O método <strong>update</strong> é moldado pela classe <strong>ResponseEntity</strong>(utilizada para representar toda a resposta HTTP, incluindo o corpo, cabeçalhos e status) que aceita objetos do tipo <strong>OrderItem</strong> e passa um argumento <strong>id</strong> que é moldado pela classe <strong>Long</strong> e tem a anotação <strong>@PathVariable</strong> que serve para capturar valores de variáveis na URL, esse <strong>id</strong> no caso é o <strong>id</strong> do item do pedido que será atualizado(o item do pedido deverá passar esse valor no momento da requisição), já o outro argumento <strong>obj</strong> irá receber os dados da atualização, a variável <strong>obj</strong> é moldada pela classe <strong>OrderItem</strong> e contém a anotação <strong>@RequestBody</strong> que serve para converter o corpo da solicitação em um objeto, esse objeto no caso é o item do pedido.
</p>

```java
@PutMapping(value = "/{id}")
public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem obj) {
```

<p>
A variável <strong>obj</strong> recebe a variável <strong>service</strong> que chama o método <strong>update</strong> da classe <strong>OrderItemService</strong> e passamos os argumentos <strong>id</strong> e <strong>obj</strong>, onde <strong>id</strong> será o id do item do pedido que será atualizado e <strong>obj</strong> será os dados que serão passados para a atualização.
</p>

```java
obj = service.update(id, obj);
```

<p>
o <strong>return</strong> abaixo está retornando um objeto do tipo ResponseEntity, que é uma classe do Spring usada para representar toda a resposta HTTP. <strong>ResponseEntity.ok()</strong> retorna um status HTTP 200 OK. O método <strong>body(obj)</strong> define o corpo da resposta como o objeto <strong>OrderItem</strong> atualizado.
</p>

```java
return ResponseEntity.ok().body(obj);
}
```
```java
}
```

### Criando Classes de Excessão para Tratar as Excessões dos Controladores

<p>
Essas classes são usadas para tratar exceções específicas que podem ocorrer durante o processamento de solicitações nos controladores.
</p>

#### ResourceExceptionHandler

<p>
As classes e métodos fornecidos estão relacionados ao tratamento de exceções em uma aplicação Spring MVC. Os métodos nesses controladores tratam exceções específicas, criando respostas adequadas para serem enviadas de volta ao cliente em caso de erro.
</p>

```java
package com.requests.project.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
```

<p>
A anotação <strong>@ControllerAdvice</strong> na classe <strong>ResourceExceptionHandler</strong> indica que a classe atua como um controlador global para lidar com exceções em uma aplicação <strong>Spring MVC</strong>. Essa anotação centraliza a lógica de tratamento de exceções, permitindo que você defina métodos anotados com <strong>@ExceptionHandler</strong> para lidar com tipos específicos de exceções. Isso evita a repetição de código de tratamento de exceções em vários controladores, simplifica a manutenção e promove uma abordagem consistente para o tratamento de erros em toda a aplicação.
 </p>

```java
@ControllerAdvice
public class ResourceExceptionHandler {
```

<p>
O método anotado com <strong>@ExceptionHandler</strong> trata exceções do tipo <strong>ResourceNotFoundException</strong>. Quando ocorre essa exceção, o método é invocado e retorna uma resposta <strong>HTTP 404 (NOT_FOUND)</strong> junto com um objeto <strong>StandardError</strong> que contém informações sobre o erro. O corpo da resposta inclui detalhes como o momento do erro, código de status, mensagem de erro padrão, mensagem específica da exceção, e a URI da requisição que originou a exceção. Essa abordagem centraliza o tratamento de exceções relacionadas a recursos não encontrados na aplicação, tornando as respostas consistentes e informativas.
</p>

```java
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	String error = "Resource not found";
	HttpStatus status = HttpStatus.NOT_FOUND;
	StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
}
```

<p>
O método anotado com <strong>@ExceptionHandler</strong> trata exceções do tipo <strong>DatabaseException</strong>. Quando ocorre essa exceção relacionada a erros no banco de dados, o método é acionado, retornando uma resposta <strong>HTTP 400 (BAD_REQUEST)</strong>. A resposta inclui um objeto <strong>StandardError</strong> com informações como o timestamp do erro, código de status, uma mensagem de erro padrão, a mensagem específica da exceção, e a <strong>URI</strong> da requisição que causou o problema. Essa abordagem centraliza o tratamento de exceções relacionadas a erros no banco de dados, proporcionando respostas consistentes e informativas em casos de falhas na interação com o banco de dados.
</p>

```java
@ExceptionHandler(DatabaseException.class)
public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
	String error = "Database error";
	HttpStatus status = HttpStatus.BAD_REQUEST;
	StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
}
```
```java
}
```

#### StandardError

<p>
Primeiro vamos definir o pacote que a classe pertence e fazer as importações necessárias.
</p>

```java
package com.requests.project.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
```

<p>
Este trecho de código em define uma classe chamada <strong>StandardError</strong> que implementa a interface <strong>Serializable</strong>. A interface <strong>Serializable</strong>e que objetos da classe sejam convertidos em bytes, útil para armazenamento ou transmissão.<br>
A linha <strong>private static final long serialVersionUID = 1L;</strong> declara uma constante <strong>serialVersionUID</strong> (identificador de versão) que ajuda a garantir a compatibilidade ao serializar e desserializar objetos. Se a estrutura da classe mudar, o <strong>serialVersionUID</strong> pode ser atualizado para indicar uma nova versão. Neste caso, o valor inicial é <strong>1L</strong>.
</p>

```java
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
```

<p>
A anotação <strong>@JsonFormat</strong> indica que o formato de serialização/desserialização JSON deve ser personalizado para o campo anotado, <strong>shape = JsonFormat.Shape.STRING</strong> especifica que o campo deve ser tratado como uma string durante a serialização. Sem essa configuração, um objeto <strong>Instant</strong> seria normalmente serializado como um número longo.
A anotação <strong>@JsonFormat</strong> a forma como o campo de timestamp é serializado para JSON, <strong>pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"</strong> define o padrão de formatação para o campo do tipo <strong>Instant</strong>, <strong>timezone = "GMT"</strong> especifica o fuso horário a ser considerado durante a serialização. Neste caso, o fuso horário é definido como <strong>GMT</strong> (Greenwich Mean Time).<br>
Embaixo da anotação fazemos um código que representa um objeto com informações relacionadas a um evento, esse evento tem seus atributos, incluindo um <strong>timestamp</strong> que informa o momento do erro, <trong>status</trong> que informa o status do erro, <strong>error</strong> que informa qual é o erro, <strong>message</strong> que informa qual a mensagem de erro e <strong>path</strong> que indica o  caminho da URL ou URI que estava sendo processado quando ocorreu o evento associado.
</p>

```java
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
```

<p>

</p>

```java
public StandardError() {
}
```

### Criando Serviços

<p>
As classes de serviço em um projeto coordenam a lógica de negócios, realizam validações, gerenciam transações e integram as entidades com os repositórios. Elas encapsulam a complexidade, promovem a reutilização de código e mantêm a coesão do sistema, facilitando a manutenção e evolução do código.
</p>

#### ProductService 

<p>
Primeiro vamos inserir o pacote que a classe pertence e fazer as importações necessárias.
</p>

```java
package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.entities.Product;
import com.requests.project.repositories.ProductRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
```

<p>
Criamos a classe <strong>ProductService</strong> e inserimos a anotação <strong>@Service</strong> encima da mesma. A anotação <strong>@Service</strong> faz com que a classe se torne serviço gerenciado pelo Spring, isso permite que outras classes possam injetar uma instância da classe quando necessário, facilitando a organização e reutilização de componentes na aplicação.
 </p>

```java
@Service
public class ProductService {
```

<p>
Fazemos uma injeção de dependência com a anotação <strong>@Autowired</strong> e agora o objeto <strong>repository</strong> nos dá acesso a classe <strong>ProductRepository</strong>.
</p>

```java
@Autowired
private ProductRepository repository;
```

<p>
Criamos o método <strong>searchAll()</strong> que é moldado por uma <strong>List</strong> (lista) que aceita objetos do tipo <strong>Product</strong> (produto), esse método tem um <strong>return</strong> (retorno) que retorna o <strong>repository</strong> chamando o método <strong>searchAll()</strong> que está dentro da classe <strong>ProductRepository</strong>.<br>
Esse método chama outro método do repositório que pega(GET) todos os produtos.
</p>

```java
public List<Product> searchAll() {
	return repository.searchAll();
}
```
<p>
Criamos também o método <strong>findById</strong> moldado pela classe <strong>Product</strong> que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o <strong>id</strong> do produto, dentro do método temos um <strong>obj</strong> moldado pela classe <strong>Optional</strong>(nos permite trabalhar com valores que podem ou não estar presentes) que aceita objetos do tipo <strong>Product</strong> e recebe o <strong>repository</strong> chamando o método <strong>findById</strong>(esse é um método pronto do Java que pega o objeto por <strong>id</strong>) que passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do produto, por fim o metodo tem um <strong>return</strong>(retorno) que traz o objeto ou uma excessão caso esse objeto não exista.
</p>

```java
public Product findById(Long id) {
	Optional<Product> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
}
```

<p>
Criamos também o método <strong>insert</strong> moldado pela classe <strong>Product</strong> que tem como argumento um <strong>obj</strong> moldado pela classe <strong>Product</strong>, esse <strong>obj</strong> no caso é o produto, por fim o método tem um <strong>return</strong>(retorno) do <strong>repository</strong>(repositório) chamando <strong>save</strong> que é um método pronto do Java que serve para salvar, repare que <strong>obj</strong> está sendo passado como argumento, esse <strong>obj</strong> é o corpo do produto.
</p>

```java
public Product insert(Product obj) {
	return repository.save(obj);
}
```

<p>
Criamos também o método <strong>delete</strong> moldado pela tipagem <strong>void</strong>(vazio) que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o id do produto. Logo no começo do método temos a abertura de um bloco <strong>try catch</strong>, este bloco serve para tentar(<strong>try</strong>) fazer alguma ação, caso a ação obtenha êxito o código segue normal, caso a ação não obtenha êxito o bloco pega(catch) o erro causado. Na abertura do <strong>try</strong> é feita a tentativa, a variável <strong>product</strong> moldada pela classe <strong>Product</strong> recebe o <strong>repository</strong>(pertencente a classe ProductRepository) que chama o método <strong>findById(id)</strong>(método pronto do Java que pega o objeto por id e como argumento está sendo passado um id que no caso é o id do produto), o método <strong>findById(id)</strong> chama o método <strong>orElseThrow(())</strong>(ou se não lançar) que faz uma expressão lambda se referindo a <strong>new ResourceNotFoundException(id)</strong>(Recurso Não Encontrado Exceção) passando <strong>id</strong> como argumento, está é uma excessão pronta do Java, ou seja, o código tenta pegar o objeto através do id e caso não consiga é lançada uma excessão que tem aquele id como argumento, depois a variável <strong>repository</strong>(pertencente a classe <strong>ProductRepository</strong>) chama o método <strong>delete</strong>(método pronto do Java que serve para deletar) que tem o <strong>product</strong> como argumento, que no caso é o produto que foi pego pelo id, ou seja, é feita a deleção do produto, no fechamento do <strong>try</strong>(tentar) é feita a abertura do catch(pegar) passando a variável <strong>e</strong> como argumento que é moldada pela classe <strong>DataIntegrityViolationException</strong>(Dados Integridade Violação Exceção, classe pronta do Java) e dentro do método existe um <strong>throw new</strong>(jogue novo) <strong>DatabaseException</strong>(Excessão de BD, classe pronta do Java) recebendo <strong>e.getMessage()</strong> como argumento, ou seja, a mensagem de excessão no BD será jogada caso seja pega alguma violação de integridade no BD e essa violação só irá acontecer caso haja alguma excessão(caso o id do produto não seja encontrado).

```java
public void delete(Long id) {
	try {
		Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repository.delete(product);
	} catch (DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
}
```

<p>
Criamos o método <strong>update</strong> com retorno do tipo <strong>Product</strong>, recebendo um <strong>id</strong> do tipo <strong>Long</strong> e um objeto <strong>Product</strong> chamado <strong>obj</strong>. O bloco <strong>try-catch</strong> é utilizado para lidar com possíveis exceções durante a execução. No início do bloco <strong>try</strong>, é feita uma tentativa de obter uma referência à entidade <strong>Product</strong> no repositório usando o método <strong>getReferenceById(id)</strong>. Esta operação pode lançar uma exceção do tipo <strong>EntityNotFoundException</strong>. Posteriormente, o método <strong>updateData(entity, obj)</strong> é chamado para atualizar os dados da entidade com base no objeto <strong>obj</strong>. Finalmente, a entidade atualizada é salva no repositório através do método <strong>repository.save(entity)</strong> é retornada. Caso uma exceção <strong>EntityNotFoundException</strong> seja capturada no bloco <strong>catch</strong>(pegar) é lançada uma exceção <strong>ResourceNotFoundException(id)</strong> para indicar que o recurso com o ID fornecido não foi encontrado. Em resumo, o método busca a entidade no repositório, atualiza seus dados com base no objeto fornecido e salva a entidade atualizada, lançando uma exceção personalizada em caso de falha na localização da entidade.

```java
public Product update(Long id, Product obj) {
	try {
		Product entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	} catch (EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
}
```

<p>
Este é um método privado chamado <strong>updateData</strong>, utilizado para atualizar os dados de um objeto <strong>Product</strong>. O método recebe dois parâmetros do tipo <strong>Product</strong>: <strong>entity</strong> (o objeto a ser atualizado) e <strong>obj</strong> (o objeto contendo os novos dados), o método realiza a atualização dos campos do objeto <strong>entity</strong> com base nos valores do objeto <strong>obj</strong>.<br>
- <strong>entity.setName(obj.getName());</strong> atualiza o nome do objeto <strong>entity</strong> com o nome do objeto <strong>obj</strong>.<br>
- <strong>entity.setDescription(obj.getDescription());</strong> atualiza a descrição do objeto <strong>entity</strong> com a descrição do objeto <strong>obj</strong>.<br>
- <strong>entity.setPrice(obj.getPrice());</strong> atualiza o preço do objeto <strong>entity</strong> com o preço do objeto <strong>obj</strong>.<br>
- <strong>entity.setImgUrl(obj.getImgUrl());</strong> atualiza a <strong>URL</strong> da imagem do objeto <strong>entity</strong> com a <strong>URL</strong> da imagem do objeto <strong>obj</strong>.<br>
- <strong>entity.setCategoryProduct(obj.getCategoryProduct());</strong> atualiza a categoria do produto do objeto <strong>entity</strong> com a categoria do objeto <strong>obj</strong>.
<p>

```java
private void updateData(Product entity, Product obj) {
	entity.setName(obj.getName());
	entity.setDescription(obj.getDescription());
	entity.setPrice(obj.getPrice());
	entity.setImgUrl(obj.getImgUrl());
	entity.setCategoryProduct(obj.getCategoryProduct());
}
```
```java
}
```

#### CategoryService

<p>
Primeiro vamos inserir o pacote que a classe pertence e fazer as importações necessárias.
</p>

```java
package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.entities.Category;
import com.requests.project.repositories.CategoryRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
```

<p>
Criamos a classe <strong>CategoryService</strong> e inserimos a anotação <strong>@Service</strong> encima da mesma. A anotação <strong>@Service</strong> faz com que a classe se torne serviço gerenciado pelo Spring, isso permite que outras classes possam injetar uma instância da classe quando necessário, facilitando a organização e reutilização de componentes na aplicação.
 </p>

```java
@Service
public class CategoryService {
```

<p>
Fazemos uma injeção de dependência com a anotação <strong>@Autowired</strong> e agora o objeto <strong>repository</strong> nos dá acesso a classe <strong>CategoryRepository</strong>.
</p>

```java
@Autowired
private CategoryRepository repository;
```

<p>
Criamos o método <strong>searchAll()</strong> que é moldado por uma <strong>List</strong> (lista) que aceita objetos do tipo <strong>Category</strong> (categoria), esse método tem um <strong>return</strong> (retorno) que retorna o <strong>repository</strong> chamando o método <strong>searchAll()</strong> que está dentro da classe <strong>CategoryRepository</strong>.<br>
Esse método chama outro método do repositório que pega(GET) todos os categorias.
</p>

```java
public List<Category> searchAll() {
	return repository.searchAll();
}
```

<p>
Criamos também o método <strong>findById</strong> moldado pela classe <strong>Category</strong> que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o <strong>id</strong> do categoria, dentro do método temos um <strong>obj</strong> moldado pela classe <strong>Optional</strong>(nos permite trabalhar com valores que podem ou não estar presentes) que aceita objetos do tipo <strong>Category</strong> e recebe o <strong>repository</strong> chamando o método <strong>findById</strong>(esse é um método pronto do Java que pega o objeto por <strong>id</strong>) que passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do categoria, por fim o metodo tem um <strong>return</strong>(retorno) que traz o objeto ou uma excessão caso esse objeto não exista.
</p>

```java
public Category findById(Long id) {
	Optional<Category> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
}
```

<p>
Criamos também o método <strong>insert</strong> moldado pela classe <strong>Category</strong> que tem como argumento um <strong>obj</strong> moldado pela classe <strong>Category</strong>, esse <strong>obj</strong> no caso é o categoria, por fim o método tem um <strong>return</strong>(retorno) do <strong>repository</strong>(repositório) chamando <strong>save</strong> que é um método pronto do Java que serve para salvar, repare que <strong>obj</strong> está sendo passado como argumento, esse <strong>obj</strong> é o corpo do categoria.
</p>

```java
public Category insert(Category obj) {
	return repository.save(obj);
}
```

<p>
Criamos também o método <strong>delete</strong> moldado pela tipagem <strong>void</strong>(vazio) que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o id do categoria. Logo no começo do método temos a abertura de um bloco <strong>try catch</strong>, este bloco serve para tentar(<strong>try</strong>) fazer alguma ação, caso a ação obtenha êxito o código segue normal, caso a ação não obtenha êxito o bloco pega(catch) o erro causado. Na abertura do <strong>try</strong> é feita a tentativa, a variável <strong>Category</strong> moldada pela classe <strong>Category</strong> recebe o <strong>repository</strong>(pertencente a classe CategoryRepository) que chama o método <strong>findById(id)</strong>(método pronto do Java que pega o objeto por id e como argumento está sendo passado um id que no caso é o id do categoria), o método <strong>findById(id)</strong> chama o método <strong>orElseThrow(())</strong>(ou se não lançar) que faz uma expressão lambda se referindo a <strong>new ResourceNotFoundException(id)</strong>(Recurso Não Encontrado Exceção) passando <strong>id</strong> como argumento, está é uma excessão pronta do Java, ou seja, o código tenta pegar o objeto através do id e caso não consiga é lançada uma excessão que tem aquele id como argumento, depois a variável <strong>repository</strong>(pertencente a classe <strong>CategoryRepository</strong>) chama o método <strong>delete</strong>(método pronto do Java que serve para deletar) que tem o <strong>Category</strong> como argumento, que no caso é a categoria que foi pega pelo id, ou seja, é feita a deleção do categoria, no fechamento do <strong>try</strong>(tentar) é feita a abertura do catch(pegar) passando a variável <strong>e</strong> como argumento que é moldada pela classe <strong>DataIntegrityViolationException</strong>(Dados Integridade Violação Exceção, classe pronta do Java) e dentro do método existe um <strong>throw new</strong>(jogue novo) <strong>DatabaseException</strong>(Excessão de BD, classe pronta do Java) recebendo <strong>e.getMessage()</strong> como argumento, ou seja, a mensagem de excessão no BD será jogada caso seja pega alguma violação de integridade no BD e essa violação só irá acontecer caso haja alguma excessão(caso o id do categoria não seja encontrada).

```java
public void delete(Long id) {
	try {
		Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repository.delete(category);
	} catch (DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
}
```

<p>
Criamos o método <strong>update</strong> com retorno do tipo <strong>Category</strong>, recebendo um <strong>id</strong> do tipo <strong>Long</strong> e um objeto <strong>Category</strong> chamado <strong>obj</strong>. O bloco <strong>try-catch</strong> é utilizado para lidar com possíveis exceções durante a execução. No início do bloco <strong>try</strong>, é feita uma tentativa de obter uma referência à entidade <strong>Category</strong> no repositório usando o método <strong>getReferenceById(id)</strong>. Esta operação pode lançar uma exceção do tipo <strong>EntityNotFoundException</strong>. Posteriormente, o método <strong>updateData(entity, obj)</strong> é chamado para atualizar os dados da entidade com base no objeto <strong>obj</strong>. Finalmente, a entidade atualizada é salva no repositório através do método <strong>repository.save(entity)</strong> é retornada. Caso uma exceção <strong>EntityNotFoundException</strong> seja capturada no bloco <strong>catch</strong>(pegar) é lançada uma exceção <strong>ResourceNotFoundException(id)</strong> para indicar que o recurso com o ID fornecido não foi encontrado. Em resumo, o método busca a entidade no repositório, atualiza seus dados com base no objeto fornecido e salva a entidade atualizada, lançando uma exceção personalizada em caso de falha na localização da entidade.

```java
public Category update(Long id, Category obj) {
	try {
		Category entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	} catch (EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
}
```

<p>
Este é um método privado chamado <strong>updateData</strong>, utilizado para atualizar os dados de um objeto <strong>Category</strong>. O método recebe dois parâmetros do tipo <strong>Category</strong>: <strong>entity</strong> (o objeto a ser atualizado) e <strong>obj</strong> (o objeto contendo os novos dados), o método realiza a atualização dos campos do objeto <strong>entity</strong> com base nos valores do objeto <strong>obj</strong>.<br>
- <strong>entity.setName(obj.getName());</strong> atualiza o nome do objeto <strong>entity</strong> com o nome do objeto <strong>obj</strong>.
<p>

```java
private void updateData(Category entity, Category obj) {
	entity.setName(obj.getName());
}
```
```java
}
```

#### OrderService

<p>
Primeiro vamos inserir o pacote que a classe pertence e fazer as importações necessárias.
</p>

```java
package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.entities.Order;
import com.requests.project.repositories.OrderRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
```

<p>
Criamos a classe <strong>OrderService</strong> e inserimos a anotação <strong>@Service</strong> encima da mesma. A anotação <strong>@Service</strong> faz com que a classe se torne um serviço gerenciado pelo Spring, isso permite que outras classes possam injetar uma instância da classe quando necessário, facilitando a organização e reutilização de componentes na aplicação.
 </p>

```java
@Service
public class OrderService {
```

<p>
Fazemos uma injeção de dependência com a anotação <strong>@Autowired</strong> e agora o objeto <strong>repository</strong> nos dá acesso a classe <strong>OrderRepository</strong>.
</p>

```java
@Autowired
private OrderRepository repository;
```

<p>
Criamos o método <strong>searchAll()</strong> que é moldado por uma <strong>List</strong> (lista) que aceita objetos do tipo <strong>Order</strong> (pedido), esse método tem um <strong>return</strong> (retorno) que retorna o <strong>repository</strong> chamando o método <strong>searchAll()</strong> que está dentro da classe <strong>OrderRepository</strong>.<br>
Esse método chama outro método do repositório que pega(GET) todos os pedidos.
</p>

```java
public List<Order> searchAll() {
	return repository.searchAll();
}
```

<p>
Criamos também o método <strong>findById</strong> moldado pela classe <strong>Order</strong> que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o <strong>id</strong> do pedido, dentro do método temos um <strong>obj</strong> moldado pela classe <strong>Optional</strong>(nos permite trabalhar com valores que podem ou não estar presentes) que aceita objetos do tipo <strong>Order</strong> e recebe o <strong>repository</strong> chamando o método <strong>findById</strong>(esse é um método pronto do Java que pega o objeto por <strong>id</strong>) que passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do pedido, por fim o metodo tem um <strong>return</strong>(retorno) que traz o objeto ou uma excessão caso esse objeto não exista.
</p>

```java
public Order findById(Long id) {
	Optional<Order> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
}
```

<p>
Criamos também o método <strong>insert</strong> moldado pela classe <strong>Order</strong> que tem como argumento um <strong>obj</strong> moldado pela classe <strong>Order</strong>, esse <strong>obj</strong> no caso é o pedido, por fim o método tem um <strong>return</strong>(retorno) do <strong>repository</strong>(repositório) chamando <strong>save</strong> que é um método pronto do Java que serve para salvar, repare que <strong>obj</strong> está sendo passado como argumento, esse <strong>obj</strong> é o corpo do pedido.
</p>

```java
public Order insert(Order obj) {
	return repository.save(obj);
}
```

<p>
Criamos também o método <strong>delete</strong> moldado pela tipagem <strong>void</strong>(vazio) que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o id do pedido. Logo no começo do método temos a abertura de um bloco <strong>try catch</strong>, este bloco serve para tentar(<strong>try</strong>) fazer alguma ação, caso a ação obtenha êxito o código segue normal, caso a ação não obtenha êxito o bloco pega(catch) o erro causado. Na abertura do <strong>try</strong> é feita a tentativa, a variável <strong>Order</strong> moldada pela classe <strong>Order</strong> recebe o <strong>repository</strong>(pertencente a classe OrderRepository) que chama o método <strong>findById(id)</strong>(método pronto do Java que pega o objeto por id e como argumento está sendo passado um id que no caso é o id do pedido), o método <strong>findById(id)</strong> chama o método <strong>orElseThrow(())</strong>(ou se não lançar) que faz uma expressão lambda se referindo a <strong>new ResourceNotFoundException(id)</strong>(Recurso Não Encontrado Exceção) passando <strong>id</strong> como argumento, está é uma excessão pronta do Java, ou seja, o código tenta pegar o objeto através do id e caso não consiga é lançada uma excessão que tem aquele id como argumento, depois a variável <strong>repository</strong>(pertencente a classe <strong>OrderRepository</strong>) chama o método <strong>delete</strong>(método pronto do Java que serve para deletar) que tem o <strong>Order</strong> como argumento, que no caso é o pedido que foi pego pelo id, ou seja, é feita a deleção do pedido, no fechamento do <strong>try</strong>(tentar) é feita a abertura do catch(pegar) passando a variável <strong>e</strong> como argumento que é moldada pela classe <strong>DataIntegrityViolationException</strong>(Dados Integridade Violação Exceção, classe pronta do Java) e dentro do método existe um <strong>throw new</strong>(jogue novo) <strong>DatabaseException</strong>(Excessão de BD, classe pronta do Java) recebendo <strong>e.getMessage()</strong> como argumento, ou seja, a mensagem de excessão no BD será jogada caso seja pega alguma violação de integridade no BD e essa violação só irá acontecer caso haja alguma excessão(caso o id do pedido não seja encontrado).

```java
public void delete(Long id) {
	try {
		Order Order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repository.delete(Order);
	} catch (DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
}
```

<p>
Criamos o método <strong>update</strong> com retorno do tipo <strong>Order</strong>, recebendo um <strong>id</strong> do tipo <strong>Long</strong> e um objeto <strong>Order</strong> chamado <strong>obj</strong>. O bloco <strong>try-catch</strong> é utilizado para lidar com possíveis exceções durante a execução. No início do bloco <strong>try</strong>, é feita uma tentativa de obter uma referência à entidade <strong>Order</strong> no repositório usando o método <strong>getReferenceById(id)</strong>. Esta operação pode lançar uma exceção do tipo <strong>EntityNotFoundException</strong>. Posteriormente, o método <strong>updateData(entity, obj)</strong> é chamado para atualizar os dados da entidade com base no objeto <strong>obj</strong>. Finalmente, a entidade atualizada é salva no repositório através do método <strong>repository.save(entity)</strong> é retornada. Caso uma exceção <strong>EntityNotFoundException</strong> seja capturada no bloco <strong>catch</strong>(pegar) é lançada uma exceção <strong>ResourceNotFoundException(id)</strong> para indicar que o recurso com o ID fornecido não foi encontrado. Em resumo, o método busca a entidade no repositório, atualiza seus dados com base no objeto fornecido e salva a entidade atualizada, lançando uma exceção personalizada em caso de falha na localização da entidade.

```java
public Order update(Long id, Order obj) {
	try {
		Order entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	} catch (EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
}
```

<p>
Este é um método privado chamado <strong>updateData</strong>, utilizado para atualizar os dados de um objeto <strong>Order</strong>. O método recebe dois parâmetros do tipo <strong>Order</strong>: <strong>entity</strong> (o objeto a ser atualizado) e <strong>obj</strong> (o objeto contendo os novos dados), o método realiza a atualização dos campos do objeto <strong>entity</strong> com base nos valores do objeto <strong>obj</strong>.<br>
- <strong>entity.setMoment(obj.getMoment());</strong> atualiza o momento do objeto <strong>entity</strong> com o momento do objeto <strong>obj</strong>.<br>
- <strong>entity.setOrderStatus(obj.getOrderStatus());</strong> atualiza o status do pedido do objeto <strong>entity</strong> com o status do pedido do objeto <strong>obj</strong>.<br>
- <strong>entity.setClient(obj.getClient());</strong> atualiza o cliente do objeto <strong>entity</strong> com o cliente do objeto <strong>obj</strong>.
<p>

```java
private void updateData(Order entity, Order obj) {
	entity.setMoment(obj.getMoment());
	entity.setOrderStatus(obj.getOrderStatus());
	entity.setClient(obj.getClient());
}
```
```java
}
```

#### UserService

<p>
Primeiro vamos inserir o pacote que a classe pertence e fazer as importações necessárias.
</p>

```java
package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.entities.User;
import com.requests.project.repositories.UserRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
```

<p>
Criamos a classe <strong>UserService</strong> e inserimos a anotação <strong>@Service</strong> encima da mesma. A anotação <strong>@Service</strong> faz com que a classe se torne o serviço gerenciado pelo Spring, isso permite que outras classes possam injetar uma instância da classe quando necessário, facilitando a organização e reutilização de componentes na aplicação.
 </p>

```java
@Service
public class UserService {
```

<p>
Fazemos uma injeção de dependência com a anotação <strong>@Autowired</strong> e agora o objeto <strong>repository</strong> nos dá acesso a classe <strong>UserRepository</strong>.
</p>

```java
@Autowired
private UserRepository repository;
```

<p>
Criamos o método <strong>searchAll()</strong> que é moldado por uma <strong>List</strong> (lista) que aceita objetos do tipo <strong>User</strong> (usuário), esse método tem um <strong>return</strong> (retorno) que retorna o <strong>repository</strong> chamando o método <strong>searchAll()</strong> que está dentro da classe <strong>UserRepository</strong>.<br>
Esse método chama outro método do repositório que pega(GET) todos os usuários.
</p>

```java
public List<User> searchAll() {
		return repository.findAll();
}
```

<p>
Criamos também o método <strong>findById</strong> moldado pela classe <strong>User</strong> que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o <strong>id</strong> do usuário, dentro do método temos um <strong>obj</strong> moldado pela classe <strong>Optional</strong>(nos permite trabalhar com valores que podem ou não estar presentes) que aceita objetos do tipo <strong>User</strong> e recebe o <strong>repository</strong> chamando o método <strong>findById</strong>(esse é um método pronto do Java que pega o objeto por <strong>id</strong>) que passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do usuário, por fim o metodo tem um <strong>return</strong>(retorno) que traz o objeto ou uma excessão caso esse objeto não exista.
</p>

```java
public User findById(Long id) {
	Optional<User> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
}
```

<p>
Criamos também o método <strong>insert</strong> moldado pela classe <strong>User</strong> que tem como argumento um <strong>obj</strong> moldado pela classe <strong>User</strong>, esse <strong>obj</strong> no caso é o usuário, por fim o método tem um <strong>return</strong>(retorno) do <strong>repository</strong>(repositório) chamando <strong>save</strong> que é um método pronto do Java que serve para salvar, repare que <strong>obj</strong> está sendo passado como argumento, esse <strong>obj</strong> é o corpo do usuário.
</p>

```java
public User insert(User obj) {
	return repository.save(obj);
}
```

<p>
Criamos também o método <strong>delete</strong> moldado pela tipagem <strong>void</strong>(vazio) que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o id do usuário. Logo no começo do método temos a abertura de um bloco <strong>try catch</strong>, este bloco serve para tentar(<strong>try</strong>) fazer alguma ação, caso a ação obtenha êxito o código segue normal, caso a ação não obtenha êxito o bloco pega(catch) o erro causado. Na abertura do <strong>try</strong> é feita a tentativa, a variável <strong>User</strong> moldada pela classe <strong>User</strong> recebe o <strong>repository</strong>(pertencente a classe <strong>UserRepository</strong>) que chama o método <strong>findById(id)</strong>(método pronto do Java que pega o objeto por id e como argumento está sendo passado um id que no caso é o id do usuário), o método <strong>findById(id)</strong> chama o método <strong>orElseThrow(())</strong>(ou se não lançar) que faz uma expressão lambda se referindo a <strong>new ResourceNotFoundException(id)</strong>(Recurso Não Encontrado Exceção) passando <strong>id</strong> como argumento, está é uma excessão pronta do Java, ou seja, o código tenta pegar o objeto através do id e caso não consiga é lançada uma excessão que tem aquele id como argumento, depois a variável <strong>repository</strong>(pertencente a classe <strong>UserRepository</strong>) chama o método <strong>delete</strong>(método pronto do Java que serve para deletar) que tem o <strong>User</strong> como argumento, que no caso é o usuário que foi pego pelo id, ou seja, é feita a deleção do usuário, no fechamento do <strong>try</strong>(tentar) é feita a abertura do catch(pegar) passando a variável <strong>e</strong> como argumento que é moldada pela classe <strong>DataIntegrityViolationException</strong>(Dados Integridade Violação Exceção, classe pronta do Java) e dentro do método existe um <strong>throw new</strong>(jogue novo) <strong>DatabaseException</strong>(Excessão de BD, classe pronta do Java) recebendo <strong>e.getMessage()</strong> como argumento, ou seja, a mensagem de excessão no BD será jogada caso seja pega alguma violação de integridade no BD e essa violação só irá acontecer caso haja alguma excessão(caso o id do usuário não seja encontrado).

```java
public void delete(Long id) {
	try {
			User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			repository.delete(user);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
```

<p>
Criamos o método <strong>update</strong> com retorno do tipo <strong>User</strong>, recebendo um <strong>id</strong> do tipo <strong>Long</strong> e um objeto <strong>User</strong> chamado <strong>obj</strong>. O bloco <strong>try-catch</strong> é utilizado para lidar com possíveis exceções durante a execução. No início do bloco <strong>try</strong>, é feita uma tentativa de obter uma referência à entidade <strong>User</strong> no repositório usando o método <strong>getReferenceById(id)</strong>. Esta operação pode lançar uma exceção do tipo <strong>EntityNotFoundException</strong>. Posteriormente, o método <strong>updateData(entity, obj)</strong> é chamado para atualizar os dados da entidade com base no objeto <strong>obj</strong>. Finalmente, a entidade atualizada é salva no repositório através do método <strong>repository.save(entity)</strong> é retornada. Caso uma exceção <strong>EntityNotFoundException</strong> seja capturada no bloco <strong>catch</strong>(pegar) é lançada uma exceção <strong>ResourceNotFoundException(id)</strong> para indicar que o recurso com o ID fornecido não foi encontrado. Em resumo, o método busca a entidade no repositório, atualiza seus dados com base no objeto fornecido e salva a entidade atualizada, lançando uma exceção personalizada em caso de falha na localização da entidade.

```java
public User update(Long id, User obj) {
	try {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	} catch (EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
}
```

<p>
Este é um método privado chamado <strong>updateData</strong>, utilizado para atualizar os dados de um objeto <strong>User</strong>. O método recebe dois parâmetros do tipo <strong>User</strong>: <strong>entity</strong> (o objeto a ser atualizado) e <strong>obj</strong> (o objeto contendo os novos dados), o método realiza a atualização dos campos do objeto <strong>entity</strong> com base nos valores do objeto <strong>obj</strong>.<br>
- <strong>entity.setName(obj.getName());</strong> atualiza o nome do objeto <strong>entity</strong> com o nome do objeto <strong>obj</strong>.<br>
- <strong>entity.setEmail(obj.getEmail());</strong> atualiza a o email do objeto <strong>entity</strong> com o email do objeto <strong>obj</strong>.<br>
- <strong>entity.setPhone(obj.getPhone());</strong> atualiza o phone do objeto <strong>entity</strong> com o phone do objeto <strong>obj</strong>.<br>
<p>

```java
private void updateData(User entity, User obj) {
	entity.setName(obj.getName());
	entity.setEmail(obj.getEmail());
	entity.setPhone(obj.getPhone());
}
```
```java
}
```

#### OrderItemService 

<p>
Primeiro vamos inserir o pacote que a classe pertence e fazer as importações necessárias.
</p>

```java
package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.dto.InterfaceOrderItem;
import com.requests.project.entities.OrderItem;
import com.requests.project.repositories.OrderItemRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
```

<p>
Criamos a classe <strong>OrderItemService</strong> e inserimos a anotação <strong>@Service</strong> encima da mesma. A anotação <strong>@Service</strong> faz com que a classe se torne serviço gerenciado pelo Spring, isso permite que outras classes possam injetar uma instância da classe quando necessário, facilitando a organização e reutilização de componentes na aplicação.
 </p>

```java
@Service
public class OrderItemService {
```

<p>
Fazemos uma injeção de dependência com a anotação <strong>@Autowired</strong> e agora o objeto <strong>repository</strong> nos dá acesso a classe <strong>OrderItemRepository</strong>
</p>

```java
@Autowired
private OrderItemRepository repository;
```

<p>
Criamos o método <strong>searchAll()</strong> que é moldado por uma <strong>List</strong> (lista) que aceita objetos do tipo <strong>OrderItem</strong> (item do pedido), esse método tem um <strong>return</strong> (retorno) que retorna o <strong>repository</strong> chamando o método <strong>searchAll()</strong> que está dentro da classe <strong>OrderItemRepository</strong>.<br>
Esse método chama outro método do repositório que pega(GET) todos os item do pedidos
</p>

```java
public List<OrderItem> searchAll() {
	return repository.searchAll();
}
```

<p>
Criamos também o método <strong>findById</strong> moldado pela classe <strong>OrderItem</strong> que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o <strong>id</strong> do item do pedido, dentro do método temos um <strong>obj</strong> moldado pela classe <strong>Optional</strong>(nos permite trabalhar com valores que podem ou não estar presentes) que aceita objetos do tipo <strong>OrderItem</strong> e recebe o <strong>repository</strong> chamando o método <strong>findById</strong>(esse é um método pronto do Java que pega o objeto por <strong>id</strong>) que passa um <strong>id</strong> como argumento, esse <strong>id</strong> no caso é o <strong>id</strong> do item do pedido, por fim o método tem um <strong>return</strong>(retorno) que traz o objeto ou uma excessão caso esse objeto não exista.
</p>

```java
public OrderItem findById(Long id) {
	Optional<OrderItem> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
}
```

<p>
Criamos também o método <strong>insert</strong> moldado pela classe <strong>OrderItem</strong> que tem como argumento um <strong>obj</strong> moldado pela classe <strong>OrderItem</strong>, esse <strong>obj</strong> no caso é o item do pedido, por fim o método tem um <strong>return</strong>(retorno) do <strong>repository</strong>(repositório) chamando <strong>save</strong> que é um método pronto do Java que serve para salvar, repare que <strong>obj</strong> está sendo passado como argumento, esse <strong>obj</strong> é o corpo do item do pedido.
</p>

```java
public OrderItem insert(OrderItem obj) {
	return repository.save(obj);
}
```

<p>
Criamos também o método <strong>delete</strong> moldado pela tipagem <strong>void</strong>(vazio) que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o id do item do pedido. Logo no começo do método temos a abertura de um bloco <strong>try catch</strong>, este bloco serve para tentar(<strong>try</strong>) fazer alguma ação, caso a ação obtenha êxito o código segue normal, caso a ação não obtenha êxito o bloco pega(catch) o erro causado. Na abertura do <strong>try</strong> é feita a tentativa, a variável <strong>OrderItem</strong> moldada pela classe <strong>OrderItem</strong> recebe o <strong>repository</strong>(pertencente a classe OrderItemRepository) que chama o método <strong>findById(id)</strong>(método pronto do Java que pega o objeto por id e como argumento está sendo passado um id que no caso é o id do item do pedido), o método <strong>findById(id)</strong> chama o método <strong>orElseThrow(())</strong>(ou se não lançar) que faz uma expressão lambda se referindo a <strong>new ResourceNotFoundException(id)</strong>(Recurso Não Encontrado Exceção) passando <strong>id</strong> como argumento, está é uma excessão pronta do Java, ou seja, o código tenta pegar o objeto através do id e caso não consiga é lançada uma excessão que tem aquele id como argumento, depois a variável <strong>repository</strong>(pertencente a classe <strong>OrderItemRepository</strong>) chama o método <strong>delete</strong>(método pronto do Java que serve para deletar) que tem o <strong>OrderItem</strong> como argumento, que no caso é o item do pedido que foi pego pelo id, ou seja, é feita a deleção do item do pedido. no fechamento do <strong>try</strong>(tentar) é feita a abertura do catch(pegar) passando a variável <strong>e</strong> como argumento que é moldada pela classe <strong>DataIntegrityViolationException</strong>(Dados Integridade Violação Exceção, classe pronta do Java) e dentro do método existe um <strong>throw new</strong>(jogue novo) <strong>DatabaseException</strong>(Excessão de BD, classe pronta do Java) recebendo <strong>(e.getMessage())</strong> como argumento, ou seja, a mensagem de excessão no BD será jogada caso seja pega alguma violação de integridade no BD e essa violação só irá acontecer caso haja alguma excessão(caso o id do item do pedido não seja encontrado).
</p>

```java
public void delete(Long id) {
	try {
		OrderItem orderItem = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repository.delete(orderItem);
	} catch (DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
}
```

<p>
Criamos o método <strong>update</strong> com retorno do tipo <strong>OrderItem</strong>, recebendo um <strong>id</strong> do tipo <strong>Long</strong> e um objeto <strong>OrderItem</strong> chamado <strong>obj</strong>. O bloco <strong>try-catch</strong> é utilizado para lidar com possíveis exceções durante a execução. No início do bloco <strong>try</strong>, é feita uma tentativa de obter uma referência à entidade <storng>OrderItem</storng> no repositório usando o método <strong>getReferenceById(id)</strong>. Esta operação pode lançar uma exceção do tipo <strong>EntityNotFoundException</strong>. Posteriormente, o método <strong>updateData(entity, obj)</strong> é chamado para atualizar os dados da entidade com base no objeto <strong>obj</strong>. Finalmente, a entidade atualizada é salva no repositório através do método <strong>repository.save(entity)</strong> é retornada.Caso uma exceção <strong>EntityNotFoundException</strong> seja capturada no bloco <strong>catch</strong>(pegar) é lançada uma exceção <strong>ResourceNotFoundException(id)</strong> para indicar que o recurso com o ID fornecido não foi encontrado. Em resumo, o método busca a entidade no repositório, atualiza seus dados com base no objeto fornecido e salva a entidade atualizada, lançando uma exceção personalizada em caso de falha na localização da entidade.

```java
public OrderItem update(Long id, OrderItem obj) {
	try {
		OrderItem entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	} catch (EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}
}
```

<p>
Este é um método privado chamado <strong>updateData</strong>, utilizado para atualizar os dados de um objeto <strong>OrderItem</strong>. O método recebe dois parâmetros do tipo <strong>OrderItem</strong>: <strong>entity</strong> (o objeto a ser atualizado) e <strong>obj</strong> (o objeto contendo os novos dados), o método realiza a atualização dos campos do objeto <strong>entity</strong> com base nos valores do objeto obj.<br>
- <strong>setQuantity(obj.getQuantity());</strong> atualiza a quantidade do objeto <strong>entity</strong> com a quantidade do objeto <strong>obj</strong>.<br>
- <strong>setPrice(obj.getPrice());</strong> atualiza a quantidade do objeto <strong>entity</strong> com o preço do objeto <strong>obj</strong>.<br>
- <strong>entity.setProductId(obj.getProductId());</strong> atualiza o produto do objeto <strong>entity</strong> com o produto do objeto <strong>obj</strong>.<br>
- <strong>entity.setOrderId(obj.getOrderId());</strong> atualiza o pedido do objeto <strong>entity</strong> com o pedido do objeto <strong>obj</strong>.
<p>

```java
private void updateData(OrderItem entity, OrderItem obj) {
	entity.setQuantity(obj.getQuantity());
	entity.setPrice(obj.getPrice());
	entity.setProductId(obj.getProductId());
	entity.setOrderId(obj.getOrderId());
}
```
```java
}
```

### Criando Classes de Excessão para Tratar as Excessões dos Serviços

<p>
Classes projetadas para tratar exceções em serviços. Essas classes se estendem a classe <strong>RuntimeException</strong>, o que sugere que são exceções não verificadas e, portanto, não exigem declaração explícita ou tratamento em tempo de compilação.Ambas as classes são úteis para lidar com exceções específicas nos serviços, proporcionando um meio de distinguir e tratar diferentes tipos de erros de maneira mais granular.
</p>

#### DatabaseException

<p>
Começamos definindo o pacote que a classe irá pertencer.
</p>

```java
package com.requests.project.services.exceptions;
```

<p>
Essas duas linhas de código pertencem à definição da classe DatabaseException.<br>
A primeira linha faz a declaração da classe <strong>DatabaseException</strong> que se estende a classe <strong>RuntimeException</strong>, isso significa que <strong>DatabaseException</strong> é uma subclasse de RuntimeException, indicando que é uma exceção não verificada.<br>
A segunda linha faz a declaração de um campo estático final chamado <strong>serialVersionUID</strong>. Esse campo é usado para fornecer uma versão única do objeto serializado, ajudando a garantir a consistência da serialização/desserialização. O valor <strong>1L</strong> é uma convenção padrão, e a presença deste campo é comum em classes serializáveis em Java.
</p>

```java
public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
```

<p>
Esse método é o construtor da classe <strong>DatabaseException</strong>.<br>
A primeira linha faz a declaração do construtor da classe <strong>DatabaseException</strong>. O construtor é público (<strong>public</strong>), o que significa que pode ser acessado de qualquer lugar no código. Ele recebe uma string (<strong>msg</strong>) como parâmetro, que geralmente contém uma mensagem que descreve a natureza da exceção.<br>
A segunda linha chama o construtor da classe pai (<strong>RuntimeException</strong>) utilizando <strong>super</strong>, passando a mensagem (<strong>msg</strong>) como argumento, isso inicializa a exceção <strong>DatabaseException</strong> com a mensagem fornecida, permitindo que informações específicas sobre o erro sejam associadas à instância da exceção.
</p>

```java
public DatabaseException(String msg) {
		super(msg);
	}
```
```java
}
```

#### ResourceNotFoundException

<p>
Começamos definindo o pacote que a classe irá pertencer.
</p>

```java
package com.requests.project.services.exceptions;
```

<p>
Essas duas linhas de código em Java referem-se à definição da classe <strong>ResourceNotFoundException</strong>.<br>
A primeira linha faz a declaração da classe <strong>ResourceNotFoundException</strong> que se estende a classe <strong>RuntimeException</strong>, isso significa que <strong>ResourceNotFoundException</strong> é uma subclasse de <strong>RuntimeException</strong>, indicando que é uma exceção não verificada.<br>
A segunda linha faz a declaração de um campo estático final chamado <strong>serialVersionUID</strong>. Esse campo é usado para fornecer uma versão única do objeto serializado, ajudando a garantir a consistência da serialização/desserialização. O valor <strong>1L</strong> é uma convenção padrão, e a presença deste campo é comum em classes serializáveis em Java.
</p>

```java
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
```

<p>
Essas duas linhas de código correspondem ao construtor da classe <strong>ResourceNotFoundException</strong> e explicam como a exceção é inicializada.<br>
A primeira linha faz a declaração do construtor da classe <strong>ResourceNotFoundException</strong>, que recebe um parâmetro do tipo <strong>Object</strong> chamado <strong>id</strong>. Este construtor é utilizado para criar instâncias da exceção, fornecendo um identificador específico do recurso que não foi encontrado.<br>
A segunda linha chama o construtor da classe pai (<strong>RuntimeException</strong>) utilizando <strong>super</strong>, passando uma mensagem de erro composta por <strong>"Resource not found. Id "</strong> concatenado com o valor do id. Isso define a mensagem associada à exceção, que pode ser útil para identificar a causa do erro ao lidar com a exceção.
</p>

```java
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
```
```java	
}
```

### Criando Classe de Teste

<p>
Essa classe serve para verificar se o contexto da aplicação Spring Boot é carregado corretamente.
</p>

#### ProjectApplicationTests

<p>
Primeiro vamos definir o pacote que a classe pertence e fazer as devidas importações necessárias.
</p>

```java
package com.requests.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
```

<p>
Criamos a classe <strong>ProjectApplicationTests</strong> e inserimos a anotação <strong>@SpringBootTest</strong> que serve para indicar que a classe deve ser configurada como um teste de integração usando o Spring Boot. O <strong>@SpringBootTest</strong> inicializa toda a aplicação Spring context para o teste, permitindo que a aplicação seja testada como um todo, incluindo configurações, beans e interações entre componentes.
</p>

```java
@SpringBootTest
class ProjectApplicationTests {
```

<p>
Criamos o método <strong>contextLoads</strong> com a anotação <strong>@Test</strong> que serve para indicar que o método é um método de teste, o tipo de retorno void indica que o método não retorna nenhum valor.
</p>

```java
@Test
void contextLoads() {
}
```
```java
}
```

### Criando Arquivos de Propiedades

<p>
Criamos o arquivo <strong>application.properties</strong> e inserimos algumas anotações
</p>

#### application.properties

<p>
Criamos o arquivo <strong>application.properties</strong> e inserimos algumas credenciais de conexão, cada credencial tem uma finalidade.<br>
A primeira anotação configura a porta do servidor(no caso a porta que a aplicação irá ocupar quando estive rodando), a segunda anotação especifica o perfil ativo da aplicação (<strong>dev</strong> é o perfil ativo, indicando que a aplicação está sendo executada no ambiente de desenvolvimento), a terceira anotação ativa ou desativa a funcionalidade Open EntityManagerInView (ou Open Session In View) para o JPA, essa funcionalidade mantém o <strong>EntityManager</strong> aberto durante a renderização da view em uma aplicação web, permitindo o acesso a dados relacionados às entidades JPA mesmo após a conclusão da transação do banco de dados, isso evita exceções de lazy loading durante a renderização da view. Por outro lado, ao definir como <strong>false</strong>, a funcionalidade é desativada, e o <strong>EntityManager</strong> é fechado após a conclusão da transação, o que pode levar a exceções de lazy loading se houver tentativas de acessar dados fora do escopo da transação.
</p>

```properties
server.port=8082

spring.profiles.active=dev

spring.jpa.open-in-view=true
```

#### application-test.properties

<p>
Criamos o arquivo <strong>application.properties</strong> e inserimos algumas credenciais de conexão, cada credencial tem uma finalidade. Essas configurações são para uma aplicação Spring Boot que utiliza um banco de dados H2 (um banco de dados em memória) e o Spring Data JPA para persistência.<br><br>

- <strong>spring.datasource.driverClassName=org.h2.Driver</strong> define a classe do driver JDBC para o H2 Database.<br>

- <strong>spring.datasource.url=jdbc:h2:mem:testdb</strong> Especifica a URL do banco de dados H2 em memória com o nome "testdb".<br>

- <strong>spring.datasource.username=sa</strong> define o nome de usuário para acessar o banco de dados. No caso do H2 em memória, o padrão é "sa" (system administrator).<br>

- <strong>spring.datasource.password=</strong> define a senha para acessar o banco de dados. No caso do H2 em memória, não é necessária uma senha.<br>

- <strong>spring.h2.console.enabled=true</strong> ativa a interface web do console H2, que permite visualizar e interagir com o banco de dados via navegador.

- <strong>spring.h2.console.path=/h2-console</strong> define o caminho (URL) para acessar o console H2.<br>

- <strong>spring.jpa.database-platform=org.hibernate.dialect.H2Dialect</strong> especifica o dialeto do Hibernate para ser usado com o H2.

- <strong>spring.jpa.defer-datasource-initialization=true</strong> adia a inicialização do datasource, útil quando se deseja personalizar ou configurar manualmente o datasource.

- <strong>spring.jpa.show-sql=true</strong> habilita a exibição das consultas SQL geradas pelo Hibernate no console de log.

- <strong>spring.jpa.properties.hibernate.format_sql=true</strong> indica ao Hibernate para formatar as consultas SQL geradas, tornando-as mais legíveis no console de log.<br>
</p>

```properties
# DATASOURCE 
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password= 
# H2 CLIENT 
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
# JPA, SQL 
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

#### application-dev.properties

<p>
Criamos o arquivo <strong>application-dev.properties</strong> e inserimos algumas credenciais de conexão, cada credencial tem uma finalidade. Essas configurações são para uma aplicação Spring Boot que utiliza um banco de dados H2 (um banco de dados em memória) e o Spring Data JPA para persistência.<br><br>

- <strong>spring.datasource.url=jdbc:postgresql://localhost:5432/springboot_systemrequests</strong> especifica a URL do banco de dados PostgreSQL local com o nome springboot_systemrequests e a porta padrão 5432.

- <strong>spring.datasource.username=postgres</strong> define o nome de usuário para acessar o banco de dados PostgreSQL.

- <strong>spring.datasource.password=1234</strong> define a senha para acessar o banco de dados PostgreSQL.

- <strong>spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true</strong> é a configuração específica para PostgreSQL, indica que a criação de objetos grandes (LOBs) não está contextualizada, permitindo manipulações fora do contexto de transação.

- <strong>spring.jpa.hibernate.ddl-auto=update</strong> atualiza automaticamente o esquema do banco de dados conforme as alterações nas entidades JPA (Java Persistence API). No entanto, é importante tomar cuidado ao usar essa configuração em ambientes de produção.

- <strong>spring.jpa.show-sql=true</strong> habilita a exibição das consultas SQL geradas pelo Hibernate no console de log.

- <strong>spring.jpa.properties.hibernate.format_sql=true</strong> indica ao Hibernate para formatar as consultas SQL geradas, tornando-as mais legíveis no console de log.

- <strong>jwt.secret=MYJWTSECRET</strong> define a chave secreta usada para assinar e verificar os tokens JWT. Essa chave é crucial para garantir a integridade e autenticidade dos tokens.

- <strong>jwt.expiration=3600000</strong> define o tempo de expiração dos tokens JWT em milissegundos. Neste exemplo, os tokens expirarão após 3600000 milissegundos, ou seja, 1 hora.
</p>

```properties
# DATASOURCE 
spring.datasource.url=jdbc:postgresql://localhost:5432/springboot_systemrequests
spring.datasource.username=postgres
spring.datasource.password=1234
# PG CLIENT 
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
# JPA, SQL 
jwt.secret=MYJWTSECRET
jwt.expiration=3600000
```

#### pom.xml

<p>
No arquivo <strong>pom.xml</strong>, são definidas as configurações do projeto Maven, incluindo as dependências, as configurações do Spring Boot, e as configurações específicas para a construção da imagem Docker usando Paketo Buildpacks.<br>

- <strong>modelVersion</strong> define a versão do modelo do POM.<br>
- <strong>parent</strong> define o projeto pai como o Spring Boot Starter Parent, herdando configurações padrão.<br>
- <strong>groupId</strong>, <strong>artifactId</strong>, <strong>version</strong>, <strong>name</strong> e <strong>description</strong> definem Informações sobre o projeto, como groupId, artifactId, versão, nome e descrição.
- <strong>properties</strong> define configurações de propriedades, neste caso, a versão do Java.
- <strong>dependencies</strong> define as dependências do projeto, incluindo Spring Boot Starter Web, Spring Boot Starter Test, Spring Boot Starter Data JPA, H2 Database, e PostgreSQL.
- <strong>build</strong> define configurações relacionadas à compilação e construção do projeto.
- <strong>plugins</strong> define configurações de plugins do Maven.
- <strong>plugin</strong> configura o plugin do Spring Boot Maven, especificando um builder para a geração da imagem Docker usando Paketo Buildpacks.
</p>

```properties
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">

	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.12</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>

	<groupId>com.requests</groupId>
	<artifactId>project</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>project</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>17</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<image>
						<builder>paketobuildpacks/builder-jammy-base:latest</builder>
					</image>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
```

### Conexão com Banco de Dados
 
<p>
Vamos criar o BD que irá armazenar os dados da aplicação, faremos isso através do SGBD <strong>pgAdmin 4</strong> que será usado para manipular o banco do PostgreSQL.<br>
Então abrimos o <strong>pgAdmin 4</strong> e cliamos em <strong>Servers</strong>, será pedida uma senha, no caso é a senha que foi definida durante a instalação do BD.
</p>

<p align="center">
    <img src="Imagens\SGBD.png">
</p>

<p>
Após realizar a conexão vamos criar o banco de dados.<br>
Clicando com o botão direito encima de <strong>Databases(1)</strong> depois em <strong>Create</strong> e <strong>Database...</strong>
</p>

<p align="center">
    <img src="Imagens\createDB.png">
</p>

<p>
Na aba <strong>General</strong> inserimos o nome do BD(lembrando que o nome tem que ser o mesmo mencionado no arquivo <strong>application-dev.properties</strong>) e selecionamos o dono
</p>

<p align="center">
    <img src="Imagens\inserirNomeEdono.png">
</p>

<p>
Nas demais abas deixamos do jeito que está, caso exista alguma configuração específica para sua aplicação pode fazer, mas deixando da forma que está sendo mostrada abaixo tudo irá ocorrer normalmente de acordo com esse manual.
</p>

<p align="center">
    <img src="Imagens\bdInstallation.png">
</p>

<p>
Repare que o BD foi criado.
</p>

<p align="center">
    <img src="Imagens\bdCriado.png">
</p>

### Realizando Testes de Funcionalidade

#### Iniciando a Aplicação

<p>
Chegou a hora de startar a aplicação.
</p>

<p align="center">
    <img src="Imagens\startApplication.png">
</p>

<p>
Quando startamos a aplicação o console irá exibir oque está sendo feito, repare que as tabelas são criadas e as contraints também, observe o retorno do console e veja os frameworks trabalhando a nosso favor:
</p>

```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
[32m :: Spring Boot :: [39m             [2m (v3.0.12)[0;39m

[2m2024-01-19T16:43:39.018-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mc.requests.project.ProjectApplication   [0;39m [2m:[0;39m Starting ProjectApplication using Java 17.0.7 with PID 8084 (C:\projetos\project\target\classes started by Lucas in C:\projetos\project)
[2m2024-01-19T16:43:39.027-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mc.requests.project.ProjectApplication   [0;39m [2m:[0;39m The following 1 profile is active: "dev"
[2m2024-01-19T16:43:40.532-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Bootstrapping Spring Data JPA repositories in DEFAULT mode.
[2m2024-01-19T16:43:40.676-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36m.s.d.r.c.RepositoryConfigurationDelegate[0;39m [2m:[0;39m Finished Spring Data repository scanning in 123 ms. Found 5 JPA repository interfaces.
[2m2024-01-19T16:43:41.720-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.s.b.w.embedded.tomcat.TomcatWebServer [0;39m [2m:[0;39m Tomcat initialized with port(s): 8082 (http)
[2m2024-01-19T16:43:41.740-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.apache.catalina.core.StandardService  [0;39m [2m:[0;39m Starting service [Tomcat]
[2m2024-01-19T16:43:41.741-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.apache.catalina.core.StandardEngine   [0;39m [2m:[0;39m Starting Servlet engine: [Apache Tomcat/10.1.15]
[2m2024-01-19T16:43:41.967-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.a.c.c.C.[Tomcat].[localhost].[/]      [0;39m [2m:[0;39m Initializing Spring embedded WebApplicationContext
[2m2024-01-19T16:43:41.968-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mw.s.c.ServletWebServerApplicationContext[0;39m [2m:[0;39m Root WebApplicationContext: initialization completed in 2786 ms
[2m2024-01-19T16:43:42.596-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.hibernate.jpa.internal.util.LogHelper [0;39m [2m:[0;39m HHH000204: Processing PersistenceUnitInfo [name: default]
[2m2024-01-19T16:43:42.846-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36morg.hibernate.Version                   [0;39m [2m:[0;39m HHH000412: Hibernate ORM core version 6.1.7.Final
[2m2024-01-19T16:43:44.153-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Starting...
[2m2024-01-19T16:43:44.517-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mcom.zaxxer.hikari.pool.HikariPool       [0;39m [2m:[0;39m HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@52f9e8bb
[2m2024-01-19T16:43:44.522-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mcom.zaxxer.hikari.HikariDataSource      [0;39m [2m:[0;39m HikariPool-1 - Start completed.
[2m2024-01-19T16:43:44.661-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mSQL dialect                             [0;39m [2m:[0;39m HHH000400: Using dialect: org.hibernate.dialect.PostgreSQLDialect
Hibernate: 
    
    create table tb_category (
       id bigserial not null,
        name varchar(255),
        primary key (id)
    )
Hibernate: 
    
    create table tb_order (
       id bigserial not null,
        moment timestamp(6) with time zone,
        order_status varchar(255),
        client_id bigint,
        primary key (id)
    )
Hibernate: 
    
    create table tb_order_item (
       id bigserial not null,
        price float(53),
        quantity integer,
        order_id bigint,
        product_id bigint,
        primary key (id)
    )
Hibernate: 
    
    create table tb_product (
       id bigserial not null,
        description varchar(255),
        img_url varchar(255),
        name varchar(255),
        price float(53),
        category_id bigint,
        primary key (id)
    )
Hibernate: 
    
    create table tb_user (
       id bigserial not null,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        phone varchar(255),
        primary key (id)
    )
Hibernate: 
    
    alter table if exists tb_order 
       add constraint FKi0x0rv7d65vsceuy33km9567n 
       foreign key (client_id) 
       references tb_user
Hibernate: 
    
    alter table if exists tb_order_item 
       add constraint FKgeobgl2xu916he8vhljktwxnx 
       foreign key (order_id) 
       references tb_order
Hibernate: 
    
    alter table if exists tb_order_item 
       add constraint FK4h5xid5qehset7qwe5l9c997x 
       foreign key (product_id) 
       references tb_product
Hibernate: 
    
    alter table if exists tb_product 
       add constraint FK8i0sq9mfbpsrabrm2pum9fspo 
       foreign key (category_id) 
       references tb_category
[2m2024-01-19T16:43:47.172-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.h.e.t.j.p.i.JtaPlatformInitiator      [0;39m [2m:[0;39m HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
[2m2024-01-19T16:43:47.192-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mj.LocalContainerEntityManagerFactoryBean[0;39m [2m:[0;39m Initialized JPA EntityManagerFactory for persistence unit 'default'
[2m2024-01-19T16:43:49.676-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mo.s.b.w.embedded.tomcat.TomcatWebServer [0;39m [2m:[0;39m Tomcat started on port(s): 8082 (http) with context path ''
[2m2024-01-19T16:43:49.694-03:00[0;39m [32m INFO[0;39m [35m8084[0;39m [2m---[0;39m [2m[           main][0;39m [36mc.requests.project.ProjectApplication   [0;39m [2m:[0;39m Started ProjectApplication in 11.643 seconds (process running for 13.235)
```

#### Como Usar o Postman para a Realização de CRUD'S

<p align="center">
    <img src="Imagens\Postman.png">
</p>

#### Realizando CRUD de INSERT(POST) na tabela tb_user

<p>
Método: POST<br>
URL:
</p>

```url
http://localhost:8082/users
```
<p>
Corpo:
</p>

```json
{
	 "name": "Manu Novaz",
	 "email": "manu@gmail.com",
	 "password": "12340",
	 "phone": "61987865445"
}
```

<p>
Retorno do Postman:
</p>

```
201 Created
```

## 2 CONCLUSÃO
    📌

    ⛏️