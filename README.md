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


------------------------------------------------------------

### ProductService 

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
Fazemos uma injeção de dependência com a anotação <strong>@Autowired</strong> e agora o objeto <strong>repository</strong> nos dá acesso a classe <strong>ProductRepository</strong>
</p>

```java
@Autowired
private ProductRepository repository;
```

<p>
Criamos o método <strong>searchAll()</strong> que é moldado por uma <strong>List</strong> (lista) que aceita objetos do tipo <strong>Product</strong> (produtos), esse método tem um <strong>return</strong> (retorno) que retorna o <strong>repository</strong> chamando o método <strong>searchAll()</strong> que está dentro da classe <strong>ProductRepository</strong>.<br>
Esse método chama outro método do repositório que pega(GET) todos os produtos
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
Criamos também o método <strong>delete</strong> moldado pela tipagem <strong>void</strong>(vazio) que tem como argumento um <strong>id</strong> moldado pela classe <strong>Long</strong>, esse <strong>id</strong> no caso é o id do produto. Logo no começo do método temos a abertura de um bloco <strong>try catch</strong>(este bloco serve para tentar(<strong>try</strong>) fazer alguma ação, caso a ação obtenha êxito o código segue normal, caso a ação não obtenha êxito o bloco pega(catch) o erro causado). Na abertura do <strong>try</strong> é feita a tentativa, a variável <strong>product</strong> moldada pela classe <strong>Product</strong> recebe o <strong>repository</strong> que chama o método <strong>findById(id)</strong>(método pronto do Java que pega o objeto por id e como argumento está sendo passado um id que no caso é o id do produto), o método <strong>findById(id)</strong> chama o método <strong>orElseThrow(()</strong>(ou se não lançar) que faz uma expressão lambda se referindo a <strong>new ResourceNotFoundException(id)</strong>(Recurso Não Encontrado Exceção) passando <strong>id</strong> como argumento, está é uma excessão pronta do Java, ou seja, o código tenta pegar o objeto através do id e caso não consiga é lançada uma excessão que tem aquele id como argumento, depois a variável <strong>repository</strong>(pertencente a classe <strong>ProductRepository</strong>) chama o método <strong>delete</strong>(método pronto do Java que serve para deletar) que tem o <strong>product</strong> como argumento, que no caso é o produto que foi pego pelo id, ou seja, é feita a deleção do produto. no fechamento do <strong>try</strong>(tentar) é feita a abertura do catch(pegar) passando a variável <strong>e</strong> como argumento que é moldada pela classe <strong>DataIntegrityViolationException</strong>(Dados Integridade Violação Exceção) e dentro do método existe um <strong>throw new</strong>(jogue novo) <strong>DatabaseException</strong>(Excessão de BD) recebendo <strong>(e.getMessage())</strong> como argumento, ou seja, a mensagem de excessão no BD será jogada caso seja pega alguma violação de integridade no BD e essa violação só irá acontecer caso haja alguma excessão(caso o id do produto não seja encontrado).

 
 por fim o método tem um <strong>return</strong>(retorno) do <strong>repository</strong>(repositório) chamando <strong>save</strong> que é um método pronto do Java que serve para salvar, repare que <strong>obj</strong> está sendo passado como argumento, esse <strong>obj</strong> é o corpo do produto.
</p>

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




<img src = "imagensedocumentos\MODELORELACIONAL.png">

<h3>
    :pushpin: Tipos de Linguagens de Banco de Dados que Serão Utilizadas
</h3>

<li>
    DQL(Linguagem de Consulta de Dados) - Permite recuperar informações específicas de um banco de dados relacional usando comandos SQL. Basicamente é o uso do SELECT(SELECIONAR).
</li>

<li>
    DDL(Linguagem de Definição de Dados) - Utilizada para definir a estrutura, organização e outras características dos dados armazenados em um banco de dados relacional. Os comandos DDL são responsáveis pela criação, modificação e exclusão dos objetos de banco de dados, tais como tabelas, colunas, índices, chaves estrangeiras, visões, entre outros. Eles não manipulam os dados propriamente ditos, apenas definem a forma como eles serão armazenados e organizados. Basicamente é o uso do CREATE(CRIAR), ALTER(ALTERAR) e DROP(DERRUBAR/APAGAR).
</li>

<li>
    DML (Linguagem de Manipulação de Dados) -  São utilizados para manipular os dados em um banco de dados ou seja, para inserir, atualizar ou excluir dados em uma tabela. Basicamente é o uso do INSERT(INSERIR), UPDATE(ATUALIZAR) e DELETE(DELETAR).
</li>

<h3>
    :pushpin: Observações Importantes
</h3>

<p>
    A cláusula * só será usada nesse projeto para economizar tempo na criação das consultas(seleções), mas vale lembrar que em um banco de dados empresarial não é aconselhável usar a cláusula *, isso pode interferir no desempenho do banco de dados, pois ao mencionar as colunas que queremos dentro da consulta o seletor já identifica as mesmas no primeiro momento, ao usar a cláusula * o seletor primeiro procura todas as colunas para depois trazer uma por uma, isso pode levar a uma sobrecarga de dados desnecessários, tornando a consulta mais lenta e consumindo mais recursos do sistema, aumentando a carga do processador e da memória. Em um banco de dados empresarial é aconselhável fazer a consulta mencionando as colunas desejadas para que essa consulta consuma menos processamento e menos memória.
</p>

<p>
    Vale lembrar também que caso algum código não funcione, basta remover os comentários e tentar rodar novamente que irá funcionar normalmente. A intenção de usar comentários nesse projeto é explicar oque cada linha de código está fazendo, mas os comentários podem acabar interferindo na sintaxe da instrução SQL, pois em alguns casos o MySQL interpreta o comentário como parte da string.
</p>

<h3>
    📌Início do Projeto Dentro do SGBD(Sistema de Gerenciamento de Banco de Dados) MySQL
</h3>

<p>
Abaixo serão apresentados os códigos usados para o desenvolvimento do projeto, mostrando do início ao fim como o banco foi criado e explicando qual a função de cada código.
</p>

<h3>
    ⛏️Criação do Banco de Dados
</h3>

```sql
CREATE DATABASE EMPRESA;/*CRIAR BANCO EMPRESA*/
``` 

<h3>
    ⛏️Criação da tabela CLIENTES dentro do banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA.CLIENTES/*CRIANDO TABELA CLIENTES DENTRO DO BANCO EMPRESA*/
(
	ID_CLIENTE INT,/*COLUNA DO TIPO INTEIRO*/
	NOME_CLIENTE VARCHAR(30) UNIQUE NOT NULL,/*COLUNA DO TIPO CARACTERE VARIAVEL, COM  ATE 30 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	CPF VARCHAR(14) NOT NULL UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL COM ATE 14 CARACTERES, VALOR UNICO E NAO PODE SER NULO*/
	SEXO ENUM('M', 'F'),/*COLUNA DO TIPO ENUMERADA, ACEITANDO APENAS OS VALORES 'M' OU 'F'*/
	PAIS VARCHAR(30) DEFAULT 'Brasil',/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES, POR PADRAO DEVE VIR O DADO 'Brasil'*/
	UF VARCHAR(2),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 2 CARACTERES*/
	CIDADE VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	BAIRRO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	ENDERECO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	TEL_RES VARCHAR(19),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 19 CARACTERES*/
	TEL_CEL VARCHAR(19) UNIQUE/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 19 CARACTERES, VALOR UNICO(NAO SE REPETE)*/
);
```

<h3>
    ⛏️Criação da tabela PRODUTOS dentro do banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA.PRODUTOS/*CRIE A TABELA PRODUTOS DENTRO DO BANCO EMPRESA*/
(
	ID_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	NOME_PROD VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	MARCA VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	PRECO DECIMAL(10, 2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES, SENDO QUE 2 DELES DEVEM VIR DEPOIS DA VIRGULA*/
	COD_BARRAS VARCHAR(13) UNIQUE/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 13 CARACTERES, VALOR UNICO(NAO SE REPETE)*/
);
```

<h3>
    ⛏️Criação da Tabela VENDAS Dentro do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA.VENDAS/*CRIANDO TABELA VENDAS DENTRO DO BANCO EMPRESA*/
(
	ID_VENDAS INT,/*COLUNA DO TIPO INTEIRO*/
	ID_VENDA_CLIENTE INT,/*COLUNA DO TIPO INTEIRO*/
	ID_VENDA_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	VALOR_DA_VENDA DECIMAL(10,2)/*COLUNA DO TIPO DECIMAL, ACEITANDO ATE 10 NUMEROS E TRAZENDO 2 DESTES NUMEROS DEPOIS DA VIRGULA*/
);
```

<h3>
    ⛏️Verificando se as Tabelas Foram Criadas Usando o MySQL Command Line Client - Unicode
</h3>

```sql
SHOW TABLES;
```
<h3>
    ⛏️Resultado da Seleção
</h3>

```sql
+-------------------+
| Tables_in_EMPRESA |
+-------------------+
| CLIENTES          |
| PRODUTOS          |
| VENDAS            |
+-------------------+
```


<h3>
    📌Para que Serve uma CONSTRAINT(RESTRIÇÃO) ?
</h3>

<p>
Uma CONSTRAINT(RESTRIÇÃO) em um banco de dados é uma regra definida para impor limitações e restrições sobre os dados armazenados em uma tabela. As CONSTRAINTS(RESTRIÇÕES) ajudam a garantir que os dados inseridos em uma tabela estejam corretos e consistentes, protegendo a integridade dos dados e evitando erros e inconsistências.
</p>

<h3>
    📌Principais funções das CONSTRAINTS(RESTRIÇÕES):
</h3>


<li>
Garantir a integridade referencial: uma CONSTRAINT(RESTRIÇÃO) de chave estrangeira pode ser usada para garantir que os dados em uma tabela relacionada a outra tabela sejam consistentes e precisos.
</li>

<li>
Limitar valores aceitáveis: uma CONSTRAINT(RESTRIÇÃO) de verificação pode ser usada para restringir os valores que podem ser inseridos em uma coluna, garantindo que apenas valores aceitáveis sejam armazenados na tabela.
</li>

<li>
Impedir duplicatas: uma CONSTRAINT(RESTRIÇÃO) de chave única pode ser usada para garantir que não haja valores duplicados em uma coluna específica de uma tabela.
</li>

<li>
Impor restrições de integridade: uma CONSTRAINT(RESTRIÇÃO) de chave primária pode ser usada para garantir que cada registro em uma tabela seja exclusivo e que haja uma chave primária definida para cada registro.
</li>

<li>
Em resumo, as CONSTRAINTS(RESTRIÇÕES) são importantes para garantir a integridade dos dados em um banco de dados, evitando erros e inconsistências que podem comprometer a qualidade dos dados e a confiabilidade das informações armazenadas.
</li>

<h3>
    ⛏️Criando CONSTRAINTS(RESTRIÇÕES) de PRIMARY KEYS(CHAVES PRIMÁRIAS)
</h3>

```sql
ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.CLIENTES/*CLIENTES DENTRO DO BANCO EMPRESA*/
ADD/*ADICIONE*/
	CONSTRAINT PK_ID_CLIENTE PRIMARY KEY(ID_CLIENTE);/*RESTRICAO TORNANDO A COLUNA ID_CLIENTE UMA CHAVE PRIMARIA*/

ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.PRODUTOS/*PRODUTOS DENTRO DO BANCO EMPRESA*/
ADD/*ADICIONE*/
	CONSTRAINT PK_ID_PROD PRIMARY KEY(ID_PROD);/*RESTRICAO TORNANDO A COLUNA ID_PROD UMA CHAVE PRIMARIA*/

ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.VENDAS/*VENDAS DENTRO DO BANCO EMPRESA*/
ADD/*ADICIONE*/
	CONSTRAINT PK_ID_VENDAS PRIMARY KEY(ID_VENDAS);/*RESTRICAO TORNANDO A COLUNA ID_VENDAS UMA CHAVE PRIMARIA*/
```

<h3>
    ⛏️Alterando as Colunas das Tabelas Fazendo com que as PRIMARY KEYS(CHAVES PRIMÁRIAS) sejam Incrementadas Automaticamente
</h3>

```sql
ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.CLIENTES/*CLIENTES DENTRO DO BANCO EMPRESA*/	 
MODIFY/*MODIFIQUE*/
	COLUMN ID_CLIENTE INT AUTO_INCREMENT;/*A COLUNA ID_CLIENTE TIPANDO COMO VALOR INTEIRO E AUTO INCREMENTAÇÃO*/

ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.PRODUTOS/*PRODUTOS DENTRO DO BANCO EMPRESA*/
MODIFY/*MODIFIQUE*/
	COLUMN ID_PROD INT AUTO_INCREMENT;/*A COLUNA ID_PROD TIPANDO COMO VALOR INTEIRO E AUTO INCREMENTAÇÃO*/

ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.VENDAS/*VENDAS DENTRO DO BANCO EMPRESA*/
MODIFY/*MODIFIQUE*/
	COLUMN ID_VENDAS INT AUTO_INCREMENT;/*A COLUNA ID_VENDAS TIPANDO COMO VALOR INTEIRO E AUTO INCREMENTAÇÃO*/
```

<h3>
    ⛏️Fazendo Relação de Tabelas Tornando as Colunas Chaves Primárias
</h3>

```sql
ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.VENDAS/*VENDAS DENTRO DO BANCO EMPRESA*/
ADD/*ADICIONE*/
	CONSTRAINT FK_VENDAS_ID_VENDA_CLIENTES_REFERENCE_CLIENTES_ID_CLIENTE/*RESTRICAO*/
	FOREIGN KEY(ID_VENDA_CLIENTE)/*CHAVE ESTRANGEIRA COLUNA ID_VENDA_CLIENTE*/
	REFERENCES CLIENTES(ID_CLIENTE);/*FARA REFERENCIA A COLUNA ID_CLIENTE DA TABELA CLIENTES*/

ALTER TABLE/*ALTERE A TABELA*/
	EMPRESA.VENDAS/*VENDAS DENTRO DO BANCO EMPRESA*/
ADD/*ADICIONE*/
	CONSTRAINT FK_VENDAS_ID_VENDA_PROD_REFERENCES_PRODUTOS_ID_PROD/*RESTRICAO*/
	FOREIGN KEY(ID_VENDA_PROD)/*CHAVE ESTRANGEIRA COLUNA ID_VENDA_PROD*/
	REFERENCES PRODUTOS(ID_PROD);/*FARA REFERENCIA A COLUNA ID_PROD DA TABELA PRODUTOS*/
```

<h3>
    📌Importância de Nomear as CONSTRAINTS(RESTRIÇÕES) que Tornam os ID's Chaves Estrangeiras
</h3>

<p>
    As CONSTRAINTS(RESTRIÇÕES) que tornam os ID's chaves entrangeiras ficam armazenadas dentro da tabela TABLE_CONSTRAINTS que fica dentro do banco INFORMATION_SCHEMA, este é um dos bancos criados automaticamente pelo MySQL para manter o funcionamento adequado do banco de dados. Nomear uma CONSTRAINT(RESTRIÇÃO) que cria uma chave estrangeira facilita o trabalho de procurar a  CONSTRAINT(RESTRIÇÃO) dentro da tabela TABLE_CONSTRAINTS, pois essa tabela armazena CONSTRAINTS(RESTRIÇÕES)s que também são criadas automaticamente pelo MySQL. Podemos inclusive fazer uma consulta dentro dessa tabela para encontrar a CONSTRAINT(RESTRIÇÃO).
</p>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	INFORMATION_SCHEMA.TABLE_CONSTRAINTS/*TABELA TABLE_CONSTRAINTS QUE ESTA DENTRO DO BANCO INFORMATION_SCHEMA*/
WHERE/*ONDE*/
	/*A COLUNA CONSTRAINT_NAME POSSUI O DADO FK_VENDAS_ID_VENDA_CLIENTES_REFERENCE_CLIENTES_ID_CLIENTE*/
	CONSTRAINT_NAME = 'FK_VENDAS_ID_VENDA_CLIENTES_REFERENCE_CLIENTES_ID_CLIENTE'
	OR/*OU*/
	/*A COLUNA CONSTRAINT_NAME POSSUI O DADO FK_VENDAS_ID_VENDA_PROD_REFERENCES_PRODUTOS_ID_PROD*/
	CONSTRAINT_NAME = 'FK_VENDAS_ID_VENDA_PROD_REFERENCES_PRODUTOS_ID_PROD';
```

<h3>
    ⛏️Resultado
</h3>

```sql
+--------------------+-------------------+-----------------------------------------------------------+--------------+------------+
| CONSTRAINT_CATALOG | CONSTRAINT_SCHEMA | CONSTRAINT_NAME                                           | TABLE_SCHEMA | TABLE_NAME |
+--------------------+-------------------+-----------------------------------------------------------+--------------+------------+
| def                | empresa           | FK_VENDAS_ID_VENDA_CLIENTES_REFERENCE_CLIENTES_ID_CLIENTE | empresa      | vendas     |
| def                | empresa           | FK_VENDAS_ID_VENDA_PROD_REFERENCES_PRODUTOS_ID_PROD       | empresa      | vendas     |
+--------------------+-------------------+-----------------------------------------------------------+--------------+------------+
```

<h3>
    ⛏️Criando Banco de Backup para Armazenar Dados que vem das TRIGGERS(GATILHOS)
</h3>

```sql
CREATE DATABASE EMPRESA_BKP;/*CRIE O BANCO EMPRESA_BKP*/
```

<p>
    <img src = "imagensedocumentos\13-bdempresabkpcriado.png">
</p>

<h3>
    ⛏️Criando Tabela que vai Armazenar Dados Inseridos Pela TRIGGER(GATILHO)
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_INSERIDOS_CLIENTES/*CRIE A TABELA DADOS_INSERIDOS_CLIENTES DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_CLIENTE INT NOT NULL,/*COLUNA DO TIPO INTEIRO E NAO PODE SER NULO*/
	NOME_CLIENTE VARCHAR(30) UNIQUE NOT NULL,/*COLUNA DO TIPO CARACTERE VARIAVEL COM ATE 30 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	CPF VARCHAR(14) NOT NULL UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL COM ATE 14 CARACTERES, VALOR UNICO E NAO PODE SER NULO*/
	SEXO ENUM('M', 'F'),/*COLUNA DO TIPO ENUMERADA, ACEITANDO APENAS OS VALORES 'M' OU 'F'*/
	PAIS VARCHAR(30) DEFAULT 'Brasil',/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES, POR PADRAO DEVE VIR O DADO 'Brasil'*/
	UF VARCHAR(2),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 2 CARACTERES*/
	CIDADE VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	BAIRRO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	ENDERECO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 30 CARACTERES*/
	TEL_RES VARCHAR(19),/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 19 CARACTERES*/
	TEL_CEL VARCHAR(19) UNIQUE/*COLUNA DO TIPO CARACTERE VARIAVEL, ACEITANDO ATE 19 CARACTERES E VALOR UNICO(NAO SE REPETE)*/
);
```

<h3>
    📌Para que Serve uma TRIGGER(GATILHO) ?
</h3>

<p>
Uma TRIGGER(GATILHO) é um objeto do banco de dados que é usado para executar automaticamente uma ação em resposta a determinados eventos que ocorrem no banco de dados. As TRIGGERS(GATILHOS) são usadas para automatizar processos de negócios, aplicar regras de negócios e manter a integridade dos dados.
</p>

<p>
As TRIGGERS(GATILHOS) podem ser definidas para executar em resposta a diferentes eventos, como a inserção, atualização ou exclusão de dados em uma tabela. As TRIGGERS(GATILHOS) podem ser usadas para realizar várias tarefas, como:
</p>

<li>
Validar dados: as TRIGGERS(GATILHOS) podem ser usadas para validar dados antes que eles sejam inseridos ou atualizados em uma tabela, garantindo que os dados sejam precisos e completos.
</li>

<li>
Atualizar dados: as TRIGGERS(GATILHOS) podem ser usadas para atualizar automaticamente os dados em uma tabela quando ocorre um evento, como a inserção ou atualização de dados em outra tabela.
</li>

<li>
Manter a integridade dos dados: as TRIGGERS(GATILHOS) podem ser usadas para garantir que os dados em uma tabela estejam sempre consistentes e corretos, evitando que valores inválidos ou duplicados sejam inseridos.
</li>

<li>
Realizar auditorias: as TRIGGERS(GATILHOS) podem ser usadas para registrar automaticamente eventos e alterações no banco de dados para fins de auditoria e rastreabilidade.
</li>

<p>
Em resumo, as TRIGGERS(GATILHOS) são usadas para automatizar processos de negócios e manter a integridade dos dados em um banco de dados. As TRIGGERS(GATILHOS) são úteis para reduzir a necessidade de intervenção manual em processos de negócios e para garantir que os dados armazenados no banco de dados sejam precisos e confiáveis.
</p>

<h3>
    ⛏️Criando TRIGGER(GATILHO) que faz Backup para a Tabela DADOS_INSERIDOS_CLIENTES do Banco EMPRESA_BKP depois de Inserir Dados na Tabela CLIENTES Dentro do Banco EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_CLIENTES_INSERIDOS/*CRIE A TRIGGER BKP_CLIENTES_INSERIDOS DENTRO DO BANCO EMPRESA*/
AFTER/*DEPOIS*/
INSERT/*DA INSERCAOT*/
	ON CLIENTES/*SOBRE A TABELA CLIENTES*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSERCAO DENTRO DE*/
	EMPRESA_BKP.DADOS_INSERIDOS_CLIENTES/*TABELA DADOS_INSERIDOS_CLIENTES QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*DOS VALORES*/
	(
		NULL,/*VALOR NULO*/
		NEW.ID_CLIENTE,/*NOVO VALOR DA COLUNA*/
		NEW.NOME_CLIENTE,/*NOVO VALOR DA COLUNA*/
		NEW.CPF,/*NOVO VALOR DA COLUNA*/
		NEW.SEXO,/*NOVO VALOR DA COLUNA*/
		NEW.PAIS,/*NOVO VALOR DA COLUNA*/
		NEW.UF,/*NOVO VALOR DA COLUNA*/
		NEW.CIDADE,/*NOVO VALOR DA COLUNA*/
		NEW.BAIRRO,/*NOVO VALOR DA COLUNA*/
		NEW.ENDERECO,/*NOVO VALOR DA COLUNA*/
		NEW.TEL_RES,/*NOVO VALOR DA COLUNA*/
		NEW.TEL_CEL/*NOVO VALOR DA COLUNA*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
    ⛏️Criando Tabela que Vai Armazenar Dados Depois do INSERT(INSERÇÃO) na Tabela PRODUTOS do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_INSERIDOS_PRODUTOS/*CRIE A TABELA DADOS_INSERIDOS_PRODUTOS DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	NOME_PROD VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	MARCA VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	PRECO DECIMAL(10,2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES, SENDO QUE 2 DELES DEVEM VIR DEPOIS DA VIRGULA*/
	COD_BARRAS VARCHAR(13) UNIQUE/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 13 CARACTERES E VALOR UNICO(NAO SE REPETE)*/
);
```

<h3>
    ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_INSERIDOS_PRODUTOS Dentro do Banco de Dados EMPRESA_BKP Depois do INSERT(INSERÇÃO) na Tabela PRODUTOS do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_PRODUTOS_INSERIDOS/*CRIE A TRIGGER BKP_PRODUTOS_INSERIDOS DENTRO DO BANCO EMPRESA*/
AFTER/*DEPOIS*/
INSERT/*DA INSERCAO*/
	ON PRODUTOS/*SOBRE A TABELA PRODUTOS*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	EMPRESA_BKP.DADOS_INSERIDOS_PRODUTOS/*TABELA DADOS_INSERIDOS_PRODUTOS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		NEW.ID_PROD,/*NOVO VALOR DA COLUNA*/
		NEW.NOME_PROD,/*NOVO VALOR DA COLUNA*/
		NEW.MARCA,/*NOVO VALOR DA COLUNA*/
		NEW.PRECO,/*NOVO VALOR DA COLUNA*/
		NEW.COD_BARRAS/*NOVO VALOR DA COLUNA*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
    ⛏️Criando Tabela que Vai Armazenar Dados Depois do INSERT(INSERÇÃO) na Tabela VENDAS do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_INSERIDOS_VENDAS/*CRIE A TABELA DADOS_INSERIDOS_VENDAS DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_VENDAS INT,/*COLUNA DO TIPO INTEIRO*/
	ID_VENDA_CLIENTE INT,/*COLUNA DO TIPO INTEIRO*/
	ID_VENDA_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	VALOR_DA_VENDA DECIMAL/*COLUNA DO TIPO DECIMAL*/
);
```

<h3>
    ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_INSERIDOS_VENDAS Dentro do Banco de Dados EMPRESA_BKP Depois do INSERT(INSERÇÃO) na Tabela VENDAS do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_VENDAS_INSERIDOS/*CRIE A TRIGGER BKP_VENDAS_INSERIDOS DENTRO DO BANCO EMRPESA*/
AFTER/*DEPOIS*/
INSERT/*DA INSERCAO*/
	ON VENDAS/*SOBRE A TABELA VENDAS*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSERCAO DENTRO DE*/
	EMPRESA_BKP.DADOS_INSERIDOS_VENDAS/*TABELA DADOS_INSERIDOS_VENDAS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*DOS VALORES*/
	(
		NULL,/*VALOR NULO*/
		NEW.ID_VENDAS,/*NOVO VALOR DA COLUNA*/
		NEW.ID_VENDA_CLIENTE,/*NOVO VALOR DA COLUNA*/
		NEW.ID_VENDA_PROD,/*NOVO VALOR DA COLUNA*/
		NEW.VALOR_DA_VENDA/*NOVO VALOR DA COLUNA*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
    ⛏️Criando Tabela que Vai Armazenar Dados Antes do DELETE(DELETAR) na Tabela CLIENTES do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_DELETADOS_CLIENTES/*CRIE A TABELA DADOS_DELETADOS_CLIENTES DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_CLIENTE INT NOT NULL,/*COLUNA DO TIPO INTEIRO E NAO PODE SER NULO*/
	NOME_CLIENTE VARCHAR(30) UNIQUE NOT NULL,/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	CPF VARCHAR(14) NOT NULL UNIQUE,/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 14 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	SEXO ENUM('M', 'F'),/*COLUNA DO TIPO ENUMERADA QUE ACEITA APENAS DOIS VALORES, SAO ESTES 'M' OU 'F'*/
	PAIS VARCHAR(30) DEFAULT 'Brasil',/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES, VALOR POR PADRAO E 'Brasil'*/
	UF VARCHAR(2),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 2 CARACTERES*/
	CIDADE VARCHAR(30),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	BAIRRO VARCHAR(30),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	ENDERECO VARCHAR(30),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	TEL_RES VARCHAR(19),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 19 CARACTERES*/
	TEL_CEL VARCHAR(19) UNIQUE/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 19 CARACTERES E VALOR UNICO(NAO SE REPETE)*/
);
```

<h3>
    ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_DELETADOS_CLIENTES Dentro do Banco de Dados EMPRESA_BKP Antes do DELETE(DELETAR) na Tabela CLIENTES do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_CLIENTES_DELETADOS/*CRIE A TRIGGER BKP_CLIENTES_DELETADOS DENTRO DO BANCO EMPRESA*/
BEFORE/*ANTES*/
DELETE/*DE DELETAR*/
	ON CLIENTES/*SOBRE A TABELA CLIENTES*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	EMPRESA_BKP.DADOS_DELETADOS_CLIENTES/*TABELA DADOS_DELETADOS_CLIENTES QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		OLD.ID_CLIENTE,/*ANTIGO VALOR DA COLUNA*/
		OLD.NOME_CLIENTE,/*ANTIGO VALOR DA COLUNA*/
		OLD.CPF,/*ANTIGO VALOR DA COLUNA*/
		OLD.SEXO,/*ANTIGO VALOR DA COLUNA*/
		OLD.PAIS,/*ANTIGO VALOR DA COLUNA*/
		OLD.UF,/*ANTIGO VALOR DA COLUNA*/
		OLD.CIDADE,/*ANTIGO VALOR DA COLUNA*/
		OLD.BAIRRO,/*ANTIGO VALOR DA COLUNA*/
		OLD.ENDERECO,/*ANTIGO VALOR DA COLUNA*/
		OLD.TEL_RES,/*ANTIGO VALOR DA COLUNA*/
		OLD.TEL_CEL/*ANTIGO VALOR DA COLUNA*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Criando Tabela que Vai Armazenar Dados Antes do DELETE(DELETAR) na Tabela PRODUTOS do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_DELETADOS_PRODUTOS/*CRIE A TABELA DADOS_DELETADOS_PRODUTOS DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/	
	ID_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	NOME_PROD VARCHAR(30),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	MARCA VARCHAR(30),/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
	PRECO DECIMAL(10,2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES SENDO QUE 2 DELES DEVEM VIR DEPOIS DA VIRGULA*/
	COD_BARRAS VARCHAR(13) UNIQUE/*COLUNA DO TIPO CARACATERE VARIAVEL, PERMITINDO ATE 13 CARACTERES, VALOR UNICO(NAO SE REPETE)*/
);
```

<h3>
    ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_DELETADOS_PRODUTOS Dentro do Banco de Dados EMPRESA_BKP Antes do DELETE(DELETAR) na Tabela PRODUTOS do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_PRODUTOS_DELETADOS/*CRIE A TRIGGER BKP_PRODUTOS_DELETADOS DENTRO DO BANCO EMPRESA*/
BEFORE/*ANTES*/
DELETE/*DE DELETAR*/
ON PRODUTOS/*SOBRE A TABELA PRODUTOS*/
FOR EACH ROW/*PARA CADA LINHA*/
BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	EMPRESA_BKP.DADOS_DELETADOS_PRODUTOS/*TABELA DADOS_DELETADOS_PRODUTOS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		OLD.ID_PROD,/*ANTIGO VALOR DA COLUNA*/
		OLD.NOME_PROD,/*ANTIGO VALOR DA COLUNA*/
		OLD.MARCA,/*ANTIGO VALOR DA COLUNA*/
		OLD.PRECO,/*ANTIGO VALOR DA COLUNA*/
		OLD.COD_BARRAS/*ANTIGO VALOR DA COLUNA*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Criando Tabela que Vai Armazenar Dados Antes do DELETE(DELETAR) na Tabela VENDAS do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_DELETADOS_VENDAS/*CRIE A TABELA DADOS_DELETADOS_VENDAS DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_VENDAS INT,/*COLUNA DO TIPO INTEIRO*/
	ID_VENDA_CLIENTE INT,/*COLUNA DO TIPO INTEIRO*/
	ID_VENDA_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	VALOR_DA_VENDA DECIMAL(10,2)/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES SENDO QUE 2 DELES DEVEM VIR DEPOIS DA VIRGULA*/
);
```

<h3>
   ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_DELETADOS_VENDAS Dentro do Banco de Dados EMPRESA_BKP Antes do DELETE(DELETAR) na Tabela VENDAS do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_VENDAS_DELETADOS/*CRIE A TRIGGER BKP_VENDAS_DELETADOS DENTRO DO BANCO EMPRESA*/
BEFORE/*ANTES*/
DELETE/*DE DELETAR*/
ON VENDAS/*SOBRE A TABELA VENDAS*/
FOR EACH ROW/*PARA CADA LINHA*/
BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	EMPRESA_BKP.DADOS_DELETADOS_VENDAS/*TABELA DADOS_DELETADOS_VENDAS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		OLD.ID_VENDAS,/*ANTIGO VALOR DA COLUNA*/
		OLD.ID_VENDA_CLIENTE,/*ANTIGO VALOR DA COLUNA*/
		OLD.ID_VENDA_PROD,/*ANTIGO VALOR DA COLUNA*/
		OLD.VALOR_DA_VENDA/*ANTIGO VALOR DA COLUNA*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Criando Tabela que Vai Armazenar Dados Depois do UPDATE(ATUALIZAÇÃO) na Tabela CLIENTES do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_ATUALIZADOS_CLIENTES/*CRIE A TABELA DADOS_ATUALIZADOS_CLIENTES DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_CLIENTE INT NOT NULL,/*COLUNA DO TIPO INTEIRO E NAO PODE SER NULO*/
	ANTIGO_NOME_CLIENTE VARCHAR(30) UNIQUE NOT NULL,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	NOVO_NOME_CLIENTE VARCHAR(30) UNIQUE NOT NULL,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	ANTIGO_CPF VARCHAR(14) NOT NULL UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 14 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	NOVO_CPF VARCHAR(14) NOT NULL UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 14 CARACTERES, VALOR UNICO(NAO SE REPETE) E NAO PODE SER NULO*/
	ANTIGO_SEXO ENUM('M', 'F'),/*COLUNA DO TIPO ENUMERADA, PERMITINDO APENAS DOIS VALORES,SAO ESTES 'M' OU 'F'*/
	NOVO_SEXO ENUM('M', 'F'),/*COLUNA DO TIPO ENUMERADA, PERMITINDO APENAS DOIS VALORES,SAO ESTES 'M' OU 'F'*/
	ANTIGO_PAIS VARCHAR(30) DEFAULT 'Brasil',/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES E O VALOR PADRAO DEVE SER 'Brasil'*/
	NOVO_PAIS VARCHAR(30) DEFAULT 'Brasil',/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES E O VALOR PADRAO DEVE SER 'Brasil'*/
	ANTIGO_UF VARCHAR(2),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 2 CARACTERES*/
	NOVO_UF VARCHAR(2),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 2 CARACTERES*/
	ANTIGO_CIDADE VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	NOVO_CIDADE VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	ANTIGO_BAIRRO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	NOVO_BAIRRO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	ANTIGO_ENDERECO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	NOVO_ENDERECO VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	ANTIGO_TEL_RES VARCHAR(19),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 19 CARACTERES*/
	NOVO_TEL_RES VARCHAR(19),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 19 CARACTERES*/
	ANTIGO_TEL_CEL VARCHAR(19) UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 19 CARACTERES E VALOR UNICO(NAO SE REPETE)*/
	NOVO_TEL_CEL VARCHAR(19) UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 19 CARACTERES E VALOR UNICO(NAO SE REPETE)*/
	DATA_HORA_ATUALIZACAO DATETIME,/*COLUNA DO TIPO DATA E HORA*/
	QUEM_ATUALIZOU VARCHAR(30)/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
);
```

<h3>
   ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_ATUALIZADOS_CLIENTES Dentro do Banco de Dados EMPRESA_BKP Depois do UPDATE(ATUALIZAÇÃO) na Tabela CLIENTES do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_CLIENTES_ATUALIZADOS/*CRIE A TRIGGER BKP_CLIENTES_ATUALIZADOS DENTRO DO BANCO EMPRESA*/
AFTER/*DEPOIS*/
UPDATE/*DA ATUALIZACAO*/
	ON CLIENTES/*SOBRE A TABELA CLIENTES*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	EMPRESA_BKP.DADOS_ATUALIZADOS_CLIENTES/*INSERCAO DENTRO DA TABELA DADOS_ATUALIZADOS_CLIENTES QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		OLD.ID_CLIENTE,/*ANTIGO VALOR DA COLUNA*/
		OLD.NOME_CLIENTE,/*ANTIGO VALOR DA COLUNA*/
		NEW.NOME_CLIENTE,/*NOVO VALOR DA COLUNA*/
		OLD.CPF,/*ANTIGO VALOR DA COLUNA*/
		NEW.CPF,/*NOVO VALOR DA COLUNA*/
		OLD.SEXO,/*ANTIGO VALOR DA COLUNA*/
		NEW.SEXO,/*NOVO VALOR DA COLUNA*/
		OLD.PAIS,/*ANTIGO VALOR DA COLUNA*/
		NEW.PAIS,/*NOVO VALOR DA COLUNA*/
		OLD.UF,/*ANTIGO VALOR DA COLUNA*/
		NEW.UF,/*NOVO VALOR DA COLUNA*/
		OLD.CIDADE,/*ANTIGO VALOR DA COLUNA*/
		NEW.CIDADE,/*NOVO VALOR DA COLUNA*/
		OLD.BAIRRO,/*ANTIGO VALOR DA COLUNA*/
		NEW.BAIRRO,/*NOVO VALOR DA COLUNA*/
		OLD.ENDERECO,/*ANTIGO VALOR DA COLUNA*/
		NEW.ENDERECO,/*NOVO VALOR DA COLUNA*/
		OLD.TEL_RES,/*ANTIGO VALOR DA COLUNA*/
		NEW.TEL_RES,/*NOVO VALOR DA COLUNA*/
		OLD.TEL_CEL,/*ANTIGO VALOR DA COLUNA*/
		NEW.TEL_CEL,/*NOVO VALOR DA COLUNA*/
		NOW(),/*DATA E HORA*/
		CURRENT_USER()/*USUARIO ATUAL/NOME DO USUARIO*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Criando Tabela que Vai Armazenar Dados Depois do UPDATE(ATUALIZAÇÃO) na Tabela PRODUTOS do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_ATUALIZADOS_PRODUTOS/*CRIE A TABELA DADOS_ATUALIZADOS_PRODUTOS DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	ANTIGO_NOME_PROD VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	NOVO_NOME_PROD VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	ANTIGO_MARCA VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	NOVO_MARCA VARCHAR(30),/*COLUNA DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	ANTIGO_PRECO DECIMAL(10,2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES, SENDO QUE 2 DELES DEVEM VIR DEPOIS DA VIRGULA*/
	NOVO_PRECO DECIMAL(10,2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES, SENDO QUE 2 DELES DEVEM VIR DEPOIS DA VIRGULA*/
	ANTIGO_COD_BARRAS VARCHAR(13) UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 13 CARACTERES E VALOR UNICO(NAO SE REPETE)*/
	NOVO_COD_BARRAS VARCHAR(13) UNIQUE,/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 13 CARACTERES E VALOR UNICO(NAO SE REPETE)*/	
	DATA_HORA_ATUALIZACAO DATETIME,/*COLUNA DO TIPO DATA E HORA*/
	QUEM_ATUALIZOU VARCHAR(30)/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
);
```

<h3>
   ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_ATUALIZADOS_PRODUTOS Dentro do Banco de Dados EMPRESA_BKP Depois do UPDATE(ATUALIZAÇÃO) na Tabela PRODUTOS do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_PRODUTOS_ATUALIZADOS/*CRIE A TRIGGER BKP_PRODUTOS_ATUALIZADOS DENTRO DO BANCO EMPRESA*/
AFTER/*DEPOIS*/
UPDATE/*DE ATUALIZAR*/
	ON PRODUTOS/*SOBRE A TABELA PRODUTOS*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	EMPRESA_BKP.DADOS_ATUALIZADOS_PRODUTOS/*TABELA DADOS_ATUALIZADOS_PRODUTOS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		OLD.ID_PROD,/*ANTIGO VALOR DA COLUNA*/
		OLD.NOME_PROD,/*ANTIGO VALOR DA COLUNA*/
		NEW.NOME_PROD,/*NOVO VALOR DA COLUNA*/
		OLD.MARCA,/*ANTIGO VALOR DA COLUNA*/
		NEW.MARCA,/*NOVO VALOR DA COLUNA*/
		OLD.PRECO,/*ANTIGO VALOR DA COLUNA*/
		NEW.PRECO,/*NOVO VALOR DA COLUNA*/
		OLD.COD_BARRAS,/*ANTIGO VALOR DA COLUNA*/
		NEW.COD_BARRAS,/*NOVO VALOR DA COLUNA*/
		NOW(),/*DATA E HORA ATUAL*/
		CURRENT_USER()/*USUARIO ATUAL/NOME DO USUARIO*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Criando Tabela que Vai Armazenar Dados Depois do UPDATE(ATUALIZAÇÃO) na Tabela VENDAS do Banco EMPRESA
</h3>

```sql
CREATE TABLE EMPRESA_BKP.DADOS_ATUALIZADOS_VENDAS/*CRIE A TABELA DADOS_ATUALIZADOS_VENDAS DENTRO DO BANCO EMPRESA_BKP*/
(
	ID INT PRIMARY KEY AUTO_INCREMENT,/*COLUNA DO TIPO INTEIRO, CHAVE PRIMARIA E AUTO INCREMENTADA*/
	ID_VENDAS INT,/*COLUNA DO TIPO INTEIRO*/
	ANTIGO_ID_VENDA_CLIENTE INT,/*COLUNA DO TIPO INTEIRO*/
	NOVO_ID_VENDA_CLIENTE INT,/*COLUNA DO TIPO INTEIRO*/
	ANTIGO_ID_VENDA_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	NOVO_ID_VENDA_PROD INT,/*COLUNA DO TIPO INTEIRO*/
	ANTIGO_VALOR_DA_VENDA DECIMAL(10,2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES, SENDO QUE 2 DELES VEM DEPOIS DA VIRGULA*/
	NOVO_VALOR_DA_VENDA DECIMAL(10,2),/*COLUNA DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES, SENDO QUE 2 DELES VEM DEPOIS DA VIRGULA*/
	DATA_HORA_ATUALIZACAO DATETIME,/*COLUNA DO TIPO DATA E HORA*/
	QUEM_ATUALIZOU VARCHAR(30)/*COLUNA DO TIPO CARACTERE VARIAVEL, PERMITINDO ATE 30 CARACTERES*/
);
```

<h3>
   ⛏️Criando TRIGGER(GATILHO) que Faz Bakcup Para a Tabela DADOS_ATUALIZADOS_VENDAS Dentro do Banco de Dados EMPRESA_BKP Depois do UPDATE(ATUALIZAÇÃO) na Tabela VENDAS do Banco de Dados EMPRESA
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE TRIGGER EMPRESA.BKP_VENDAS_ATUALIZADOS/*CRIE A TRIGGER BKP_VENDAS_ATUALIZADOS DENTRO DO BANCO EMPRESA*/
AFTER/*DEPOIS*/
UPDATE/*DE ATUALIZAR */
	ON VENDAS/*SOBRE A TABELA VENDAS*/
	FOR EACH ROW/*PARA CADA LINHA*/
	BEGIN/*INICIE*/
INSERT INTO/*INSERCAO DENTRO DE*/
	EMPRESA_BKP.DADOS_ATUALIZADOS_VENDAS/*TABELA DADOS_ATUALIZADOS_VENDAS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		NEW.ID_VENDAS,/*NOVO VALOR DA COLUNA*/
		OLD.ID_VENDA_CLIENTE,/*ANTIGO VALOR DA COLUNA*/
		NEW.ID_VENDA_CLIENTE,/*NOVO VALOR DA COLUNA*/
		OLD.ID_VENDA_PROD,/*ANTIGO VALOR DA COLUNA*/
		NEW.ID_VENDA_PROD,/*NOVO VALOR DA COLUNA*/
		OLD.VALOR_DA_VENDA,/*ANTIGO VALOR DA COLUNA*/
		NEW.VALOR_DA_VENDA,/*NOVO VALOR DA COLUNA*/
		NOW(),/*DATA E HORA ATUAL*/
		CURRENT_USER()/*USUARIO ATUAL/NOME DO USUARIO*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   📌Para que Serve uma PROCEDURE(PROCEDIMENTO) ?
</h3>

<p>
Uma PROCEDURE(PROCEDIMENTO) (também conhecida como PROC ou procedimento armazenado) é um objeto de banco de dados que contém um conjunto de instruções SQL que são agrupadas em uma única unidade lógica de trabalho. Ela é uma função que pode ser invocada por um programa ou script para realizar uma série de tarefas predefinidas no banco de dados.
</p>

<p>
As PROCEDURES(PROCEDIMENTOS) são úteis para uma variedade de tarefas, como:
</p>

<li>
Reutilização de código: Ao escrever as instruções SQL em uma PROCEDURE(PROCEDIMENTO), essas instruções podem ser reutilizadas em vários pontos do sistema de banco de dados, reduzindo a necessidade de duplicar o código.
</li>

<li>
Controle de acesso: As PROCEDURES(PROCEDIMENTOS) permitem que um administrador de banco de dados conceda e revogue privilégios de acesso a uma determinada PROCEDURE(PROCEDIMENTO), em vez de conceder privilégios de acesso às tabelas individuais do banco de dados.
</li>

<li>
Melhor desempenho: Ao invocar uma PROCEDURE(PROCEDIMENTO), o banco de dados pode armazenar a PROCEDURE(PROCEDIMENTO) em cache, permitindo que ela seja executada mais rapidamente em comparação com a execução de um conjunto de instruções SQL individuais.
</li>

<li>
Implementação de regras de negócios: As PROCEDURES(PROCEDIMENTOS) permitem que as regras de negócios sejam implementadas diretamente no banco de dados, garantindo que os dados estejam sempre de acordo com as políticas e regras da empresa.
</li>

<p>
Em resumo, uma PROCEDURE(PROCEDIMENTO) é uma maneira de agrupar um conjunto de instruções SQL em uma unidade lógica de trabalho que pode ser invocada por um programa ou script. As PROCEDURES(PROCEDIMENTOS) são úteis para melhorar o desempenho do banco de dados, implementar regras de negócios e controlar o acesso aos dados.
</p>

<h3>
    ⛏️Criando PROCEDURE(PROCEDIMENTO) que Insere Parâmetros com Dados Dentro da Tabela CLIENTES
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE PROCEDURE EMPRESA.INSERT_TAB_CLIENTES/*CRIE O PROCEDIMENTO INSERT_TAB_CLIENTES DENTRO DO BANCO EMPRESA*/
(
	NOME_CLIENTE VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	CPF VARCHAR(14),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 14 CARACTERES*/
	SEXO ENUM('M', 'F'),/*PARAMETRO DO TIPO ENUMERADA E PERMITINDO APENAS 2 CARACTERES, SENDO ESTES 'M' OU 'F'*/
	PAIS VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	UF VARCHAR(2),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 2 CARACTERES*/
	CIDADE VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	BAIRRO VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	ENDERECO VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	TEL_RES VARCHAR(19),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 19 CARACTERES*/
	TEL_CEL VARCHAR(19)/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 19 CARACTERES*/
) 
BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	CLIENTES/*TABELA CLIENTES*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR*/
		NOME_CLIENTE,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		CPF,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		SEXO,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		PAIS,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		UF,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		CIDADE,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		BAIRRO,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		ENDERECO,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		TEL_RES,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		TEL_CEL/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
    ⛏️Chamando PROCEDURE(PROCEDIMENTO) e Inserindo Dados Dentro dos Parâmetros que Serão Inseridos na Tabela CLIENTES
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'CAIO ALMEIDA MELO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'123.965.445-41',/*VALOR PARA OCUPAR O PARAMETRO*/
	'M',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ANAPOLIS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ANAPOLIM',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 12 QUADRA 2',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)3293-4418',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99714-4574'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'LUCAS ALFREDO MELO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'456.965.445-42',/*VALOR PARA OCUPAR O PARAMETRO*/
	'M',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ANAPOLIS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ANAPOLIM',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 12 QUADRA 3',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)3697-4477',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99999-4574'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'MARIA JUSSARA PINTO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'789.965.445-43',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'MA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'CAJARI',/*VALOR PARA OCUPAR O PARAMETRO*/
	'CAJADOS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 5 QUADRA 2',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)3737-4778',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99778-7884'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'MARLOM JOSE DA CRUZ',/*VALOR PARA OCUPAR O PARAMETRO*/
	'101.965.445-44',/*VALOR PARA OCUPAR O PARAMETRO*/
	'M',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'MA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'AÇAILANDIA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'CAMPINA SERRA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 12 QUADRA 25',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)4040-4418',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99663-4563'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'JUSSARA DIVINA SOUZA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'211.965.445-45',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'MT',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ANAPOLIS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BOM BAIRRO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 1 QUADRA 2',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)4545-4418',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99696-4576'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'MOISES PAULO SILVA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'311.965.485-46',/*VALOR PARA OCUPAR O PARAMETRO*/
	'M',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ABAIRA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'DOCE AXE',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 9 QUADRA 7',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)4511-1010',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99665-1121'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'MARIO JOSE DA COSTA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'312.165.445-47',/*VALOR PARA OCUPAR O PARAMETRO*/
	'M',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ADUSTINA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GELTIL SILVA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 7 QUADRA 7',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)3111-1515',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99714-1113'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'ROSA SANTOS SOARES',/*VALOR PARA OCUPAR O PARAMETRO*/
	'313.265.445-48',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'SC',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ABDON BATISTA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'MAGALHADO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 22 QUADRA 23',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)3939-4747',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99898-4574'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'LARISSA PAULA GUEDES',/*VALOR PARA OCUPAR O PARAMETRO*/
	'314.365.445-49',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'MG',/*VALOR PARA OCUPAR O PARAMETRO*/
	'ABREUS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'CAMPINAS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA FINIM QUADRA 2',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)4574-3918',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)99097-4664'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'PRISCILA DA COSTA NUNES',/*VALOR PARA OCUPAR O PARAMETRO*/
	'992.965.445-44',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BELGICA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BX',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRUXELAS',/*VALOR PARA OCUPAR O PARAMETRO*/
	'NODES',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA JULIO ALMEDIDA QUADRA 8',/*VALOR PARA OCUPAR O PARAMETRO*/
	NULL,/*VALOR*/
	'+55(62)99636-4888'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'ARLINDA CRUZ COELHO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'128.875.145-99',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GOIANIA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'JARDIM VITORIA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 4 QUADRA 2',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)4315-3918',/*VALOR PARA OCUPAR O PARAMETRO*/
	'+55(62)98455-1111'/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_CLIENTES/*CHAMANDO PROCEDURE*/
(
	'AMANDA NUNES BARBOSA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'888.915.433-30',/*VALOR PARA OCUPAR O PARAMETRO*/
	'F',/*VALOR PARA OCUPAR O PARAMETRO*/
	'BRASIL',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'GOIANIA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'JARDIM ABREU',/*VALOR PARA OCUPAR O PARAMETRO*/
	'RUA 4 QUADRA 5',/*VALOR PARA OCUPAR O PARAMETRO*/
	NULL,/*VALOR*/
	'+55(62)98787-4861'/*VALOR PARA OCUPAR O PARAMETRO*/
);
```

<h3>
    ⛏️Fazendo SELECT(SELEÇÃO) para Visualizar os Dados Inseridos Dentro da Tabela CLIENTES
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.CLIENTES;/*TABELA CLIENTES DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+----------------------+----------------+------+--------+----+------------------+----------------+--------------------+------------------+-------------------+
| ID | 	   NOME_CLIENTE     |       CPF      | SEXO |  PAIS  | UF |      CIDADE      |     BAIRRO     |     ENDERECO       |     TEL_RES      |      TEL_CEL      |
+----+----------------------+----------------+------+--------+----+------------------+----------------+--------------------+------------------+-------------------+
| 1  | CAIO ALMEIDA MELO    | 123.965.445-41 |   M  | BRASIL | GO |    ANAPOLIS      | ANAPOLIM       | RUA 12 QUADRA 2    | +55(62)3293-4418 | +55(62)99714-4574 |
| 2  | LUCAS ALFREDO MELO   | 456.965.445-42 |   M  | BRASIL | GO |    ANAPOLIS      | ANAPOLIM       | RUA 12 QUADRA 3    | +55(62)3697-4477 | +55(62)99999-4574 |
| 3  | MARIA JUSSARA PINTO  | 789.965.445-43 |   F  | BRASIL | MA |    CAJARI        | CAJADOS        | RUA 5 QUADRA 2     | +55(62)3737-4778 | +55(62)99778-7884 |
| 4  | MARLOM JOSE DA CRUZ  | 101.965.445-44 |   M  | BRASIL | MA |    AÇAILANDIA    | CAMPINA SERRA  | RUA 12 QUADRA 25   | +55(62)4040-4418 | +55(62)99663-4563 |
| 5  | JUSSARA DIVINA SOUZA | 211.965.445-45 |   F  | BRASIL | MT |    ANAPOLIS      | BOM BAIRRO     | RUA 1 QUADRA 2     | +55(62)4545-4418 | +55(62)99696-4576 |
| 6  | MOISES PAULO SILVA   | 311.965.485-46 |   M  | BRASIL | BA |    ABAIRA        | DOCE AXE       | RUA 9 QUADRA 7     | +55(62)4511-1010 | +55(62)99665-1121 |
| 7  | MARIO JOSE DA COSTA  | 312.165.445-47 |   M  | BRASIL | BA |    ADUSTINA      | GELTIL SILVA   | RUA 7 QUADRA 7     | +55(62)3111-1515 | +55(62)99714-1113 |
| 8  | ROSA SANTOS SOARES   | 313.265.445-48 |   F  | BRASIL | SC |    ABDON BATISTA | MAGALHADO      | RUA 22 QUADRA 23   | +55(62)3939-4747 | +55(62)99898-4574 |
| 9  | LARISSA PAULA GUEDES | 314.365.445-49 |   F  | BRASIL | MG |    ABREUS        | CAMPINAS       | RUA FINIM QUADRA 2 | +55(62)4574-3918 | +55(62)99097-4664 |
| 10 | PRISCILA DA COSTA    | 992.965.445-44 |   F  | BELGICA| BX |    BRUXELAS      | NODES          |                    |                  | +55(62)99636-4888 |
| 11 | ARLINDA CRUZ COELHO  | 128.875.145-99 |   F  | BRASIL | GO |    GOIANIA       | JARDIM VITORIA | RUA 4 QUADRA 2     | +55(62)4315-3918 | +55(62)98455-1111 |
| 12 | AMANDA NUNES BARBOSA | 888.915.433-30 |   F  | BRASIL | GO |    GOIANIA       | JARDIM ABREU   | RUA 4 QUADRA 5     |                  | +55(62)98787-4861 |
+----+----------------------+----------------+------+--------+----+------------------+----------------+--------------------+------------------+-------------------+
```

<h3>
    ⛏️Criando PROCEDURE(PROCEDIMENTO) que Insere Parâmetros com Dados Dentro da Tabela PRODUTOS
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE PROCEDURE EMPRESA.INSERT_TAB_PRODUTOS/*CRIE O PROCEDIMENTO INSERT_TAB_PRODUTOS DENTRO DO BANCO EMPRESA*/
(
	NOME_PROD VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	MARCA VARCHAR(30),/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 30 CARACTERES*/
	PRECO DECIMAL(10,2),/*PARAMETRO DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES SENDO QUE 2 DEVEM VIR DEPOIS DA VIRGULA*/
	COD_BARRAS VARCHAR(13)/*PARAMETRO DO TIPO CARACTERE VARIAVEL E PERMITINDO ATE 13 CARACTERES*/
) 
BEGIN/*INICIE*/
INSERT INTO/*INSIRA DENTRO DE*/
	PRODUTOS/*TABELA PRODUTOS*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR*/
		NOME_PROD,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		MARCA,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		PRECO,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		COD_BARRAS/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Chamando PROCEDURE(PROCEDIMENTO) e Inserindo Dados Dentro dos Parâmetros que Serão Inseridos na Tabela PRODUTOS
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'POCCO X3 PRO',/*VALOR PARA OCUPAR O PARAMETRO*/
	'XIAOMI',/*VALOR PARA OCUPAR O PARAMETRO*/
	1400.00,/*VALOR PARA OCUPAR O PARAMETRO*/
	784512454547/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'GALAXY Z FLIP4',/*VALOR PARA OCUPAR O PARAMETRO*/
 	'SAMSUNG',/*VALOR PARA OCUPAR O PARAMETRO*/
 	 2145.00,/*VALOR PARA OCUPAR O PARAMETRO*/
 	 123123456784/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'P26',/*VALOR PARA OCUPAR O PARAMETRO*/
	'POSITIVO',/*VALOR PARA OCUPAR O PARAMETRO*/
	75.30,/*VALOR PARA OCUPAR O PARAMETRO*/
	451478965233/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'S22 ULTRA',/*VALOR PARA OCUPAR O PARAMETRO*/
	'POSITIVO',/*VALOR PARA OCUPAR O PARAMETRO*/
	602.99,/*VALOR PARA OCUPAR O PARAMETRO*/
	47895656523/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'IPHONE 13 PRO MAX',/*VALOR PARA OCUPAR O PARAMETRO*/
	'APPLE',/*VALOR PARA OCUPAR O PARAMETRO*/
	8999.90,/*VALOR PARA OCUPAR O PARAMETRO*/
	315896547831/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'C20',/*VALOR PARA OCUPAR O PARAMETRO*/
	'NOKIA',/*VALOR PARA OCUPAR O PARAMETRO*/
	573.00,/*VALOR PARA OCUPAR O PARAMETRO*/
	789444475454/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'UP PLAY',/*VALOR PARA OCUPAR O PARAMETRO*/
	'MULTILASER',/*VALOR PARA OCUPAR O PARAMETRO*/
	94.00,/*VALOR PARA OCUPAR O PARAMETRO*/
	741526487777/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'HUAWEI X8 SILVER',/*VALOR PARA OCUPAR O PARAMETRO*/
	'HONOR',/*VALOR PARA OCUPAR O PARAMETRO*/
	1330.12,/*VALOR PARA OCUPAR O PARAMETRO*/
	321452145784/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'LG K61',/*VALOR PARA OCUPAR O PARAMETRO*/
	'LG',/*VALOR PARA OCUPAR O PARAMETRO*/
	1249.00,/*VALOR PARA OCUPAR O PARAMETRO*/
	741256353535/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_PRODUTOS/*CHAMANDO PROCEDURE*/
(
	'LG K52',/*VALOR PARA OCUPAR O PARAMETRO*/
	'LG',/*VALOR PARA OCUPAR O PARAMETRO*/
	989.90,/*VALOR PARA OCUPAR O PARAMETRO*/
	745896565652/*VALOR PARA OCUPAR O PARAMETRO*/
);
```

<h3>
    ⛏️Fazendo SELECT(SELEÇÃO) para Visualizar os Dados Inseridos Dentro da Tabela PRODUTOS
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.PRODUTOS;/*TABELA PRODUTOS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+--------------------+-------------+--------+---------------+
|     NOME_PROD      |    MARCA    | PRECO  |   COD_BARRAS  |
+--------------------+-------------+--------+---------------+
|   POCCO X3 PRO     |   XIAOMI    | 1400.0 | 784512454547  |
| GALAXY Z FLIP4     |   SAMSUNG   | 2145.0 | 123123456784  |
|        P26         |   POSITIVO  |  75.3  | 451478965233  |
|     S22 ULTRA      |   POSITIVO  | 602.99 |  47895656523  |
| IPHONE 13 PRO MAX  |   APPLE     | 8999.9 | 315896547831  |
|        C20         |   NOKIA     | 573.0  | 789444475454  |
|      UP PLAY       | MULTILASER  |  94.0  | 741526487777  |
| HUAWEI X8 SILVER   |    HONOR    | 1330.12| 321452145784  |
|       LG K61       |     LG      | 1249.0 | 741256353535  |
|       LG K52       |     LG      | 989.9  | 745896565652  |
+--------------------+-------------+--------+---------------+
```

<h3>
   ⛏️Criando PROCEDURE(PROCEDIMENTO) que Insere Parâmetros com Dados Dentro da Tabela VENDAS
</h3>

```sql
DELIMITER $/*ALTERANDO DELIMITADOR*/
CREATE PROCEDURE EMPRESA.INSERT_TAB_VENDAS/*CRIE O PROCEDIMENTO INSERT_TAB_VENDAS DENTRO DO BANCO EMPRESA*/
(
	ID_VENDA_CLIENTE INT,/*PARAMETRO DO TIPO INTEIRO*/
	ID_VENDA_PROD INT,/*PARAMETRO DO TIPO INTEIRO*/
	VALOR_DA_VENDA DECIMAL(10,2)/*PARAMETRO DO TIPO DECIMAL, PERMITINDO ATE 10 CARACTERES SENDO QUE 2 DEVEM VIR DEPOIS DA VIRGULA*/
) 
BEGIN/*INICIE*/
INSERT INTO/*INSERCAO DENTRO DE*/
	VENDAS/*TABELA VENDAS*/
VALUES/*OS VALORES*/
	(
		NULL,/*VALOR NULO*/
		ID_VENDA_CLIENTE,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		ID_VENDA_PROD,/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
		VALOR_DA_VENDA/*PARAMETRO QUE IRA RECEBER UM VALOR NA CHAMADA DA PROCEDURE*/
	);
END/*FIM*/
$/*ENCERRANDO COM DELIMITADOR*/
```

<h3>
   ⛏️Chamando PROCEDURE(PROCEDIMENTO) e Inserindo Dados Dentro dos Parâmetros que Serão Inseridos na Tabela VENDAS
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	1,/*VALOR PARA OCUPAR O PARAMETRO*/
	2,/*VALOR PARA OCUPAR O PARAMETRO*/
	2145.00/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	2,/*VALOR PARA OCUPAR O PARAMETRO*/
	3,/*VALOR PARA OCUPAR O PARAMETRO*/
	75.30/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	3,/*VALOR PARA OCUPAR O PARAMETRO*/
	3,/*VALOR PARA OCUPAR O PARAMETRO*/
	75.30/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	4,/*VALOR PARA OCUPAR O PARAMETRO*/
	3,/*VALOR PARA OCUPAR O PARAMETRO*/
	75.30/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	5,/*VALOR PARA OCUPAR O PARAMETRO*/
 	4,/*VALOR PARA OCUPAR O PARAMETRO*/
 	602.99/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	6,/*VALOR PARA OCUPAR O PARAMETRO*/
	5,/*VALOR PARA OCUPAR O PARAMETRO*/
	8999.90/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	7,/*VALOR PARA OCUPAR O PARAMETRO*/
	6,/*VALOR PARA OCUPAR O PARAMETRO*/
	573.00/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	8,/*VALOR PARA OCUPAR O PARAMETRO*/
	7,/*VALOR PARA OCUPAR O PARAMETRO*/
	94.00/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	9,/*VALOR PARA OCUPAR O PARAMETRO*/
	8,/*VALOR PARA OCUPAR O PARAMETRO*/
	1330.12/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	10,/*VALOR PARA OCUPAR O PARAMETRO*/
	9,/*VALOR PARA OCUPAR O PARAMETRO*/
	1249.00/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	10,/*VALOR PARA OCUPAR O PARAMETRO*/
	1,/*VALOR PARA OCUPAR O PARAMETRO*/
	1400.00/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	10,/*VALOR PARA OCUPAR O PARAMETRO*/
	2,/*VALOR PARA OCUPAR O PARAMETRO*/
	2145.00/*VALOR PARA OCUPAR O PARAMETRO*/
);

CALL INSERT_TAB_VENDAS/*CHAMANDO PROCEDURE*/
(
	10,/*VALOR PARA OCUPAR O PARAMETRO*/
	4,/*VALOR PARA OCUPAR O PARAMETRO*/
	602.99/*VALOR PARA OCUPAR O PARAMETRO*/
);
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) para Visualizar os Dados Inseridos Dentro da Tabela VENDAS
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.VENDAS;/*TABELA VENDAS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+------------+--------------------------+-----------------+----------------+
| ID _VENDAS | ID _VENDA_CLIENTE        | ID _VENDA_PROD  | VALOR_DA_VENDA | 
+------------+--------------------------+-----------------+----------------+
| 1          | 1                        | 2               | 2145.00        | 
| 2          | 2                        | 3               | 75.30          | 
| 3          | 3                        | 3               | 75.30          | 
| 4          | 4                        | 3               | 75.30          | 
| 5          | 5                        | 4               | 602.99         | 
| 6          | 6                        | 5               | 8999.90        | 
| 7          | 7                        | 6               | 573.00         | 
| 8          | 8                        | 7               | 94.00          | 
| 9          | 9                        | 8               | 1330.12        | 
| 10         | 10                       | 9               | 1249.00        | 
| 11         | 10                       | 1               | 1400.00        | 
| 12         | 10                       | 2               | 2145.00        | 
| 13         | 10                       | 4               | 602.99         | 
+------------+--------------------------+-----------------+----------------+
```

<h3>
    ⛏️Fazendo SELECT(SELEÇÃO) para Ver se Houve Backup dos Dados Inseridos e se a TRIGGER(GATILHO) BKP_CLIENTES_INSERIDOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_INSERIDOS_CLIENTES;/*TABELA DADOS_INSERIDOS_CLIENTES DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
| ID | ID_CLIENTE |    NOME_CLIENTE      |       CPF      | SEXO |  PAIS  | UF |      CIDADE      |     BAIRRO     |     ENDERECO                |     TEL_RES      |      TEL_CEL      |
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
| 1  |      1     | CAIO ALMEIDA MELO    | 123.965.445-41 |   M  | BRASIL | GO |    ANAPOLIS      | ANAPOLIM       | RUA 12 QUADRA 2             | +55(62)3293-4418 | +55(62)99714-4574 |
| 2  |      2     | LUCAS ALFREDO MELO   | 456.965.445-42 |   M  | BRASIL | GO |    ANAPOLIS      | ANAPOLIM       | RUA 12 QUADRA 3             | +55(62)3697-4477 | +55(62)99999-4574 |
| 3  |      3     | MARIA JUSSARA PINTO  | 789.965.445-43 |   F  | BRASIL | MA |    CAJARI        | CAJADOS        | RUA 5 QUADRA 2              | +55(62)3737-4778 | +55(62)99778-7884 |
| 4  |      4     | MARLOM JOSE DA CRUZ  | 101.965.445-44 |   M  | BRASIL | MA |    AÇAILANDIA    | CAMPINA SERRA  | RUA 12 QUADRA 25            | +55(62)4040-4418 | +55(62)99663-4563 |
| 5  |      5     | JUSSARA DIVINA SOUZA | 211.965.445-45 |   F  | BRASIL | MT |    ANAPOLIS      | BOM BAIRRO     | RUA 1 QUADRA 2              | +55(62)4545-4418 | +55(62)99696-4576 |
| 6  |      6     | MOISES PAULO SILVA   | 311.965.485-46 |   M  | BRASIL | BA |    ABAIRA        | DOCE AXE       | RUA 9 QUADRA 7              | +55(62)4511-1010 | +55(62)99665-1121 |
| 7  |      7     | MARIO JOSE DA COSTA  | 312.165.445-47 |   M  | BRASIL | BA |    ADUSTINA      | GELTIL SILVA   | RUA 7 QUADRA 7              | +55(62)3111-1515 | +55(62)99714-1113 |
| 8  |      8     | ROSA SANTOS SOARES   | 313.265.445-48 |   F  | BRASIL | SC |    ABDON BATISTA | MAGALHADO      | RUA 22 QUADRA 23            | +55(62)3939-4747 | +55(62)99898-4574 |
| 9  |      9     | LARISSA PAULA GUEDES | 314.365.445-49 |   F  | BRASIL | MG |    ABREUS        | CAMPINAS       | RUA FINIM QUADRA 2          | +55(62)4574-3918 | +55(62)99097-4664 |
| 10 |     10     | PRISCILA DA COSTA    | 992.965.445-44 |   F  | BELGICA| BX |    BRUXELAS      | NODES          | RUA JULIO ALMEDIDA QUADRA 8 |                  | +55(62)99636-4888 |
| 11 |     11     | ARLINDA CRUZ COELHO  | 128.875.145-99 |   F  | BRASIL | GO |    GOIANIA       | JARDIM VITORIA | RUA 4 QUADRA 2              | +55(62)4315-3918 | +55(62)98455-1111 |
| 12 |     12     | AMANDA NUNES BARBOSA | 888.915.433-30 |   F  | BRASIL | GO |    GOIANIA       | JARDIM ABREU   | RUA 4 QUADRA 5              |                  | +55(62)98787-4861 |
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) para Ver se Houve Backup dos Dados Inseridos e se a TRIGGER(GATILHO) BKP_PRODUTOS_INSERIDOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_INSERIDOS_PRODUTOS;/*TABELA DADOS_INSERIDOS_PRODUTOS DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+---------+-------------------+------------+---------+--------------+
| ID | ID_PROD | NOME_PROD         | MARCA      | PRECO   | COD_BARRAS   | 
+----+---------+-------------------+------------+---------+--------------+
| 1  | 1       | POCCO X3 PRO      | XIAOMI     | 1400.00 | 784512454547 |          
| 2  | 2       | GALAXY Z FLIP4    | SAMSUNG    | 2145.00 | 123123456784 |        
| 3  | 3       | P26               | POSITIVO   | 75.30   | 451478965233 |
| 4  | 4       | S22 ULTRA         | POSITIVO   | 602.99  | 47895656523  |       
| 5  | 5       | IPHONE 13 PRO MAX | APPLE      | 8999.90 | 315896547831 | 
| 6  | 6       | C20               | NOKIA      | 573.00  | 789444475454 |            
| 7  | 7       | UP PLAY           | MULTILASER | 94.00   | 741526487777 |
| 8  | 8       | HUAWEI X8 SILVER  | HONOR      | 1330.12 | 321452145784 |
| 9  | 9       | LG K61            | LG         | 1249.00 | 741256353535 |
| 10 | 10      | LG K52            | LG         | 989.90  | 745896565652 |       
+----+---------+-------------------+------------+---------+--------------+
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>


```sql
+----+-----------+------------------+---------------+----------------+
| ID | ID_VENDAS | ID_VENDA_CLIENTE | ID_VENDA_PROD | VALOR_DA_VENDA |
+----+-----------+------------------+---------------+----------------+
| 1  | 1         | 1                | 2             | 2145           |        
| 2  | 2         | 2                | 3             | 75             |        
| 3  | 3         | 3                | 3             | 75             |
| 4  | 4         | 4                | 3             | 75             |       
| 5  | 5         | 5                | 4             | 603            |
| 6  | 6         | 6                | 5             | 9000           |         
| 7  | 7         | 7                | 6             | 573            |
| 8  | 8         | 8                | 7             | 94             | 
| 9  | 9         | 9                | 8             | 1330           |
| 10 | 10        | 10               | 9             | 1249           |     
| 11 | 11        | 10               | 1             | 1400           | 
| 12 | 12        | 10               | 2             | 2145           | 
| 13 | 13        | 10               | 4             | 603            | 
+----+-----------+------------------+---------------+----------------+
```

<h3>
	📌Observações Importantes Antes de Iniciar os Comandos de DELETE(DELETAR).
</h3>

<p>
	Se for deletar um cliente e o mesmo estiver coligado a alguma venda na tabela VENDAS, primeiro é necessário deletar esse cliente da tabela VENDAS para depois deletar esse cliente da tabela CLIENTES.
</p>

<p>
	Se for deletar um produto e o mesmo estiver coligado a alguma venda na tabela VENDAS, primeiro é necessário deletar esse produto da tabela VENDAS para depois deletar esse produto da tabela PRODUTOS.
</p>

<h3>
    ⛏️Fazendo DELETE(DELETANDO) Dentro da Tabela VENDAS que Fica Dentro do Banco EMPRESA
</h3>

```sql
DELETE FROM/*DELETE DENTRO DE*/
	EMPRESA.VENDAS/*TABELA VENDAS QUE FICA DENTRO DO BANCO EMPRESA*/
WHERE/*ONDE*/
	ID_VENDAS BETWEEN 1 AND 6;/*COLUNA ID_VENDAS TENHA O VALOR ENTRE 1 E 6*/
```

<h3>
   ⛏️Fazendo DELETE(DELETANDO) Dentro da Tabela PRODUTOS que Fica Dentro do Banco EMPRESA
</h3>

```sql
DELETE FROM/*DELETE DENTRO DE*/
	EMPRESA.PRODUTOS/*TABELA PRODUTOS QUE FICA DENTRO DO BANCO EMPRESA*/
WHERE/*ONDE*/
	ID_PROD = 3;/*ID_PROD SEJA IGUAL A 3*/
```

<h3>
   ⛏️Fazendo DELETE(DELETANDO) Dentro da Tabela CLIENTES que Fica Dentro do Banco EMPRESA
</h3>

```sql
DELETE FROM/*DELETE DENTRO DE*/
	EMPRESA.CLIENTES/*TABELA CLIENTES QUE FICA DENTRO DO BANCO EMPRESA*/
WHERE/*ONDE*/
	ID_CLIENTE = 1;/*ID_CLIENTE E IGUAL A 1*/
```

<h3>
    ⛏️Fazendo SELECT(SELEÇÃO) Dentro da Tabela CLIENTES Para Verificar se os Dados Foram Deletados
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/ 
FROM /*DENTRO DE*/
	EMPRESA.CLIENTES;/*TABELA CLIENTES DENTRO DO BANCO EMPRESA*/
```

<h3>
    ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
| ID | ID_CLIENTE |    NOME_CLIENTE      |       CPF      | SEXO |  PAIS  | UF |      CIDADE      |     BAIRRO     |     ENDERECO                |     TEL_RES      |      TEL_CEL      |
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
| 2  |      2     | LUCAS ALFREDO MELO   | 456.965.445-42 |   M  | BRASIL | GO |    ANAPOLIS      | ANAPOLIM       | RUA 12 QUADRA 3             | +55(62)3697-4477 | +55(62)99999-4574 |
| 3  |      3     | MARIA JUSSARA PINTO  | 789.965.445-43 |   F  | BRASIL | MA |    CAJARI        | CAJADOS        | RUA 5 QUADRA 2              | +55(62)3737-4778 | +55(62)99778-7884 |
| 4  |      4     | MARLOM JOSE DA CRUZ  | 101.965.445-44 |   M  | BRASIL | MA |    AÇAILANDIA    | CAMPINA SERRA  | RUA 12 QUADRA 25            | +55(62)4040-4418 | +55(62)99663-4563 |
| 5  |      5     | JUSSARA DIVINA SOUZA | 211.965.445-45 |   F  | BRASIL | MT |    ANAPOLIS      | BOM BAIRRO     | RUA 1 QUADRA 2              | +55(62)4545-4418 | +55(62)99696-4576 |
| 6  |      6     | MOISES PAULO SILVA   | 311.965.485-46 |   M  | BRASIL | BA |    ABAIRA        | DOCE AXE       | RUA 9 QUADRA 7              | +55(62)4511-1010 | +55(62)99665-1121 |
| 7  |      7     | MARIO JOSE DA COSTA  | 312.165.445-47 |   M  | BRASIL | BA |    ADUSTINA      | GELTIL SILVA   | RUA 7 QUADRA 7              | +55(62)3111-1515 | +55(62)99714-1113 |
| 8  |      8     | ROSA SANTOS SOARES   | 313.265.445-48 |   F  | BRASIL | SC |    ABDON BATISTA | MAGALHADO      | RUA 22 QUADRA 23            | +55(62)3939-4747 | +55(62)99898-4574 |
| 9  |      9     | LARISSA PAULA GUEDES | 314.365.445-49 |   F  | BRASIL | MG |    ABREUS        | CAMPINAS       | RUA FINIM QUADRA 2          | +55(62)4574-3918 | +55(62)99097-4664 |
| 10 |     10     | PRISCILA DA COSTA    | 992.965.445-44 |   F  | BELGICA| BX |    BRUXELAS      | NODES          | RUA JULIO ALMEDIDA QUADRA 8 |                  | +55(62)99636-4888 |
| 11 |     11     | ARLINDA CRUZ COELHO  | 128.875.145-99 |   F  | BRASIL | GO |    GOIANIA       | JARDIM VITORIA | RUA 4 QUADRA 2              | +55(62)4315-3918 | +55(62)98455-1111 |
| 12 |     12     | AMANDA NUNES BARBOSA | 888.915.433-30 |   F  | BRASIL | GO |    GOIANIA       | JARDIM ABREU   | RUA 4 QUADRA 5              |                  | +55(62)98787-4861 |
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) Dentro da Tabela PRODUTOS Para Verificar se os Dados Foram Deletados
</h3>

```sql
SELECT/*SELECIONE*/ 
	*/*TUDO*/ 
FROM/*DENTRO DE*/
	EMPRESA.PRODUTOS;/*TABELA PRODUTOS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----------+-------------------+------------+---------+--------------+
| ID_PROD  | NOME_PROD         | MARCA      | PRECO   | COD_BARRAS   |
+----------+-------------------+------------+---------+--------------+
| 1        | POCCO X3 PRO      | XIAOMI     | 1400.00 | 784512454547 |
| 2        | GALAXY Z FLIP4    | SAMSUNG    | 2145.00 | 123123456784 |
| 4        | S22 ULTRA         | POSITIVO   | 602.99  | 47895656523  |
| 5        | IPHONE 13 PRO MAX | APPLE      | 8999.90 | 315896547831 |
| 6        | C20               | NOKIA      | 573.00  | 789444475454 |
| 7        | UP PLAY           | MULTILASER | 94.00   | 741526487777 |
| 8        | HUAWEI X8 SILVER  | HONOR      | 1330.12 | 321452145784 |
| 9        | LG K61            | LG         | 1249.00 | 741256353535 |
| 10       | LG K52            | LG         | 989.90  | 745896565652 |
+----------+-------------------+------------+---------+--------------+
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) Dentro da Tabela VENDAS Para Verificar se os Dados Foram Deletados
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.VENDAS;/*TABELA VENDAS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+------------+--------------------------+-----------------+----------------+
| ID _VENDAS | ID _VENDA_CLIENTE        | ID _VENDA_PROD  | VALOR_DA_VENDA | 
+------------+--------------------------+-----------------+----------------+
| 7          | 7                        | 6               | 573.00         | 
| 8          | 8                        | 7               | 94.00          | 
| 9          | 9                        | 8               | 1330.12        | 
| 10         | 10                       | 9               | 1249.00        | 
| 11         | 10                       | 1               | 1400.00        | 
| 12         | 10                       | 2               | 2145.00        | 
| 13         | 10                       | 4               | 602.99         | 
+------------+--------------------------+-----------------+----------------+
```

<h3>
    ⛏️Fazendo SELECT(SELEÇÃO) Para Ver se Houve Backup dos Dados Deletados e se a TRIGGER(GATILHO) BKP_CLIENTES_DELETADOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_DELETADOS_CLIENTES;/*TABELA DADOS_DELETADOS_CLIENTES QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
| ID | ID_CLIENTE |    NOME_CLIENTE      |       CPF      | SEXO |  PAIS  | UF |      CIDADE      |     BAIRRO     |     ENDERECO                |     TEL_RES      |      TEL_CEL      |
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
| 1  | 1          | CAIO ALMEIDA MELO    | 123.965.445-41 |   M  | BRASIL | GO |    ANAPOLIS      | ANAPOLIM       | RUA 12 QUADRA 2             | +55(62)3293-4418 | +55(62)99714-4574 |
+----+------------+----------------------+----------------+------+--------+----+------------------+----------------+-----------------------------+------------------+-------------------+
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) Para Ver se Houve Backup dos Dados Deletados e se a TRIGGER(GATILHO) BKP_PRODUTOS_DELETADOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_DELETADOS_PRODUTOS;/*TABELA DADOS_DELETADOS_PRODUTOS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+---------+-------------------+------------+---------+--------------+
| ID | ID_PROD | NOME_PROD         | MARCA      | PRECO   | COD_BARRAS   |
+----+---------+-------------------+------------+---------+--------------+
| 1  | 3       | P26               | POSITIVO   | 75.30   | 451478965233 |
+----+---------+-------------------+------------+---------+--------------+
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) Para Ver se Houve Backup dos Dados Deletados e se a TRIGGER(GATILHO) BKP_VENDAS_DELETADOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_DELETADOS_VENDAS;/*TABELA DADOS_DELETADOS_VENDAS QUE FICA DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+-----------+------------------+---------------+----------------+
| ID | ID_VENDAS | ID_VENDA_CLIENTE | ID_VENDA_PROD | VALOR_DA_VENDA |
+----+-----------+------------------+---------------+----------------+
| 1  | 1         | 1                | 2             | 2145.00        |        
| 2  | 2         | 2                | 3             | 75.30          |        
| 3  | 3         | 3                | 3             | 75.30          |
| 4  | 4         | 4                | 3             | 75.30          |       
| 5  | 5         | 5                | 4             | 602.99         |
| 6  | 6         | 6                | 5             | 8999.90        |         
+----+-----------+------------------+---------------+----------------+
```

<h3>
    ⛏️Fazendo UPDATE(ATUALIZAÇÃO) Dentro da Tabela CLIENTES que Fica Dentro do Banco EMPRESA
</h3>

```sql
UPDATE/*ATUALIZE*/
	EMPRESA.CLIENTES/*TABELA CLIENTES QUE FICA DENTRO DO BANCO EMPRESA*/
SET/*SETANDO*/
	NOME_CLIENTE = 'JULIA BONITA',/*COLUNA = VALOR*/
	CPF = '741.963.852-87',/*COLUNA = VALOR*/
	SEXO = 'F',/*COLUNA = VALOR*/
	PAIS = 'HONDURAS',/*COLUNA = VALOR*/
	UF = 'DF',/*COLUNA = VALOR*/
	CIDADE = 'TAGUATINGA',/*COLUNA = VALOR*/
	BAIRRO = 'TAGUATINGA NORTE',/*COLUNA = VALOR*/
	ENDERECO = 'QD 2 LT 5',/*COLUNA = VALOR*/
	TEL_RES = '+55(62)3369-4417',/*COLUNA = VALOR*/
	TEL_CEL = '+55(61)99798-7847'/*COLUNA = VALOR*/
WHERE/*ONDE*/
	ID_CLIENTE = 2;/*ID_CLIENTE E IGUAL A 2*/
```

<h3>
    ⛏️Fazendo SELECT(SELEÇÃO) na Tabela DADOS_ATUALIZADOS_CLIENTES Dentro do Banco EMPRESA_BKP Para Visualizar se a TRIGGER(GATILHO) BKP_CLIENTES_ATUALIZADOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_ATUALIZADOS_CLIENTES;/*TABELA DADOS_ATUALIZADOS_CLIENTES DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----+------------+---------------------+-------------------+----------------+----------------+-------------+-----------+-------------+-----------+-----------+---------+---------------+-------------+---------------+--------------------+-----------------+---------------+------------------+------------------+-------------------+-------------------+-----------------------+----------------+
| ID | ID_CLIENTE | ANTIGO_NOME_CLIENTE | NOVO_NOME_CLIENTE | ANTIGO_CPF     | NOVO_CPF       | ANTIGO_SEXO | NOVO_SEXO | ANTIGO_PAIS | NOVO_PAIS | ANTIGO_UF | NOVO_UF | ANTIGO_CIDADE | NOVO_CIDADE | ANTIGO_BAIRRO | NOVO_BAIRRO        | ANTIGO_ENDERECO | NOVO_ENDERECO | ANTIGO_TEL_RES   | NOVO_TEL_RES     | ANTIGO_TEL_CEL    | NOVO_TEL_CEL      | DATA_HORA_ATUALIZACAO | QUEM_ATUALIZOU |
+----+------------+---------------------+-------------------+----------------+----------------+-------------+-----------+-------------+-----------+-----------+---------+---------------+-------------+---------------+--------------------+-----------------+---------------+------------------+------------------+-------------------+-------------------+-----------------------+----------------+
| 1  | 2          | LUCAS ALFREDO MELO  | JULIA BONITA      | 456.965.445-42 | 741.963.852-87 | M           | F         | BRASIL      | HONDURAS  | GO        | DF      | ANAPOLIS      | TAGUATINGA  | ANAPOLIM      | TAGUATINGA NORTE   | RUA 12 QUADRA 3 | QD 2 LT 5     | +55(62)3697-4477 | +55(62)3369-4417 | +55(62)99999-4574 | +55(61)99798-7847 | 2023-04-24 13:54:57   | root@localhost |
+----+------------+---------------------+-------------------+----------------+----------------+-------------+-----------+-------------+-----------+-----------+---------+---------------+-------------+---------------+--------------------+-----------------+---------------+------------------+------------------+-------------------+-------------------+-----------------------+----------------+
```

<h3>
   ⛏️Fazendo UPDATE(ATUALIZAÇÃO) Dentro da Tabela PRODUTOS que Fica Dentro do Banco EMPRESA
</h3>

```sql
UPDATE/*ATUALIZE*/
	EMPRESA.PRODUTOS/*TABELA PRODUTOS DENTRO DO BANCO EMPRESA*/
SET/*SETANDO*/
	NOME_PROD = 'POCCO X4 PRO',/*COLUNA = VALOR*/
	MARCA = 'XIAOMI EVOLUTION',/*COLUNA = VALOR*/
	PRECO = 1600.00,/*COLUNA = VALOR*/
	COD_BARRAS = 745214523652/*COLUNA = VALOR*/
WHERE/*ONDE*/
	ID_PROD = 2;/*ONDE O ID_PROD E IGUAL A 2*/
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) na Tabela DADOS_ATUALIZADOS_PRODUTOS Dentro do Banco EMPRESA_BKP Para Visualizar se a TRIGGER(GATILHO) BKP_PRODUTOS_ATUALIZADOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_ATUALIZADOS_PRODUTOS;/*TABELA DADOS_ATUALIZADOS_PRODUTOS DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql

+-----------------+-----------------+--------------------------+-----------------------+-----------------+-----------------------+-----------------+-----------------+-------------------------+-----------------------+-------------------------------+---------------------+
|        ID       |     ID_PROD     |   ANTIGO_NOME_PROD       |    NOVO_NOME_PROD     |   ANTIGO_MARCA  |     NOVO_MARCA        |  ANTIGO_PRECO   |    NOVO_PRECO   |   ANTIGO_COD_BARRAS     |   NOVO_COD_BARRAS     | DATA_HORA_ATUALIZACAO         |   QUEM_ATUALIZOU    |
+-----------------+-----------------+--------------------------+-----------------------+-----------------+-----------------------+-----------------+-----------------+-------------------------+-----------------------+-------------------------------+---------------------+
|        1        |        2        |  GALAXY Z FLIP4          |   POCCO X4 PRO        |    SAMSUNG      |   XIAOMI EVOLUTION    |     2145.00     |     1600.00     |   123123456784          |   745214523652        |   2023-04-24 14:07:25         |  root@localhost     |
+-----------------+-----------------+--------------------------+-----------------------+-----------------+-----------------------+-----------------+-----------------+-------------------------+-----------------------+-------------------------------+---------------------+
```

<h3>
   ⛏️Fazendo UPDATE(ATUALIZAÇÃO) Dentro da Tabela VENDAS que Fica Dentro do Banco EMPRESA
</h3>

```sql
UPDATE/*ATUALIZAR*/
	EMPRESA.VENDAS/*TABELA VENDAS DENTRO DO BANCO EMPRESA*/
SET/*SETANDO*/
	ID_VENDA_CLIENTE = 2,/*COLUNA = VALOR*/
	ID_VENDA_PROD = 1,/*COLUNA = VALOR*/
	VALOR_DA_VENDA = 1400.00/*COLUNA = VALOR*/
WHERE/*ONDE*/
	ID_VENDAS = 7;/*O ID_VENDAS E IGUAL A 7*/
```

<h3>
   ⛏️Fazendo SELECT(SELEÇÃO) na Tabela DADOS_ATUALIZADOS_VENDAS Dentro do Banco EMPRESA_BKP Para Visualizar se a TRIGGER(GATILHO) BKP_VENDAS_ATUALIZADOS Funcionou
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA_BKP.DADOS_ATUALIZADOS_VENDAS;/*TABELA DADOS_ATUALIZADOS_VENDAS DENTRO DO BANCO EMPRESA_BKP*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+-----+-----------+-------------------------+-----------------------+----------------------+--------------------+-----------------------+---------------------+-----------------------+----------------+
| ID  | ID_VENDAS | ANTIGO_ID_VENDA_CLIENTE | NOVO_ID_VENDA_CLIENTE | ANTIGO_ID_VENDA_PROD | NOVO_ID_VENDA_PROD | ANTIGO_VALOR_DA_VENDA | NOVO_VALOR_DA_VENDA | DATA_HORA_ATUALIZACAO | QUEM_ATUALIZOU |
+-----+-----------+-------------------------+-----------------------+----------------------+--------------------+-----------------------+---------------------+-----------------------+----------------+
| 1   | 7         |7                        | 2                     | 6                    | 1                  | 573.00                | 1400.00             | 2023-04-24 14:38:33   | root@localhost |
+-----+-----------+-------------------------+-----------------------+----------------------+--------------------+-----------------------+---------------------+-----------------------+----------------+
```

<h3>
    📌Para que serve uma VIEW(VISUALIZAÇÃO) ?
</h3>

<p>
Uma VIEW(VISUALIZAÇÃO) é uma tabela virtual em um banco de dados relacional que não é armazenada fisicamente. Ela é uma consulta predefinida que retorna um conjunto específico de dados selecionados a partir de uma ou mais tabelas do banco de dados.
</p>

<p>
As VIEWS(VISUALIZAÇÕES) têm várias finalidades e benefícios, incluindo:
</p>

<p>
Simplificação das consultas: As VIEWS(VISUALIZAÇÕES) podem ser usadas para agrupar e simplificar as consultas complexas que envolvem várias tabelas do banco de dados. Em vez de escrever consultas longas e complexas, é possível criar VIEWS(VISUALIZAÇÕES) que retornem apenas as informações relevantes.
</p>

<p>
Segurança de dados: As VIEWS(VISUALIZAÇÕES) podem ser usadas para restringir o acesso a dados confidenciais em uma tabela do banco de dados. Por exemplo, uma VIEW(VISUALIZAÇÃO) pode ser criada para mostrar apenas as informações que os usuários têm permissão para acessar, ocultando os dados confidenciais.
</p>

<p>
Facilidade de manutenção: As VIEWS(VISUALIZAÇÕES) podem ser atualizadas facilmente sem afetar as tabelas subjacentes do banco de dados. Por exemplo, se uma tabela é renomeada ou suas colunas são alteradas, a VIEW(VISUALIZAÇÃO) pode ser atualizada para refletir essas alterações sem alterar a consulta original.
</p>

<p>
Desempenho: As VIEWS(VISUALIZAÇÕES) podem melhorar o desempenho do banco de dados, pois os dados são armazenados em cache e a consulta é pré-compilada. Isso significa que a VIEW(VISUALIZAÇÃO) pode ser acessada mais rapidamente do que uma consulta que precisa ser executada cada vez que é solicitada.
</p>

<p>
Em resumo, as VIEWS(VISUALIZAÇÕES) são consultas predefinidas que retornam um conjunto específico de dados selecionados a partir de uma ou mais tabelas do banco de dados. Elas têm várias finalidades, incluindo simplificar as consultas, aumentar a segurança de dados, facilitar a manutenção e melhorar o desempenho do banco de dados.
</p>

<h3>
   ⛏️Criando da VIEW(VISUALIZAÇÃO) REL_VENDAS que Mostra qual Cliente Comprou qual Produto e qual Foi o Valor da Venda
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW REL_VENDAS AS/*CRIE A VIEW REL_VENDAS*/
SELECT/*SELECIONE*/
	V.ID_VENDAS,/*COLUNA ID_VENDAS DENTRO DA TABELA VENDAS*/
	C.NOME_CLIENTE,/*COLUNA NOME_CLIENTE DENTRO DA TABELA CLIENTES*/
	P.NOME_PROD,/*COLUNA NOME_PROD DENTRO DA TABELA PRODUTOS*/
	V.VALOR_DA_VENDA/*COLUNA VALOR_DA_VENDA DENTRO DA TABELA VENDAS*/
FROM/*DENTRO DE*/
	VENDAS V/*TABELA VENDAS APELIDADA COMO V*/
	INNER JOIN CLIENTES C/*JUNCAO INTERNA DA TABELA CLIENTES APELIDADA COMO C*/
	ON V.ID_VENDA_CLIENTE = C.ID_CLIENTE/*SOBRE O VALOR DA COLUNA ID_VENDA_CLIENTE(DENTRO DA TABELA VENDAS) SENDO IGUAL AO VALOR DA COLUNA ID_CLIENTE(DENTRO DA TABELA CLIENTES)*/
	INNER JOIN PRODUTOS P/*JUNCAO INTERNA DA TABELA PRODUTOS APELIDADA COMO P*/
	ON V.ID_VENDA_PROD = P.ID_PROD/*SOBRE O VALOR DA COLUNA ID_VENDA_PROD(DENTRO DA TABELA VENDAS) SENDO IGUAL AO VALOR DA COLUNA ID_PROD(DENTRO DA TABELA PRODUTOS)*/
ORDER BY/*ORDENAR POR*/
	V.ID_VENDAS;/*COLUNA ID_VENDAS DENTRO DA TABELA VENDAS*/
```

<h3>
   ⛏️Selecionando a VIEW(VISUALIZAÇÃO) REL_VENDAS Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.REL_VENDAS;/*VIEW REL_VENDAS QUE ESTA DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+------------+--------------------------+------------------+----------------+
| ID _VENDAS | NOME_CLIENTE             | NOME_PROD        | VALOR_DA_VENDA | 
+------------+--------------------------+------------------+----------------+
| 1          | JULIA BONITA             | POCCO X3 PRO     | 2              | 
| 1          | ROSA SANTOS SOARES       | UP PLAY          | 2              | 
| 1          | LARISSA PAULA GUEDES     | HUAWEI X8 SILVER | 2              | 
| 1          | PRISCILA DA COSTA NUNES  | LG K61           | 2              | 
| 1          | PRISCILA DA COSTA NUNES  | POCCO X3 PRO     | 2              | 
| 1          | PRISCILA DA COSTA NUNES  | POCCO X4 PRO     | 2              | 
| 1          | PRISCILA DA COSTA NUNES  | S22 ULTRA        | 2              | 
+------------+--------------------------+------------------+----------------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) REL_VENDAS que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETANDO SELECAO*/
	'ID_VENDAS',
	'NOME_CLIENTE',
	'NOME_PROD',
	'VALOR_DA_VENDA'
UNION ALL/*UNINDO TODAS*/
SELECT/*SELECIONE AS COLUNAS*/
	ID_VENDAS,
	NOME_CLIENTE,
	NOME_PROD,
	VALOR_DA_VENDA
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.REL_VENDAS.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	LINES TERMINATED BY '\n'/*LINHAS TERMINADAS POR*/
FROM/*DENTRO DE*/
	EMPRESA.REL_VENDAS;/*VIEW REL_VENDAS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\76-printdatabelarelvendasexportada.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1PBtPzx2byb0n699g9ndaGZL7whhXm1uA/view?usp=share_link
</p>

<h3>
    ⛏️Criando VIEW(VISUALIZAÇÃO) CALC_REL_VENDAS que Mostra o Resultado de Alguns Cálculos Feitos na VIEW(VISUALIZAÇÃO) REL_VENDAS
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW EMPRESA.CALC_REL_VENDAS AS/*CRIE A VIEW CALC_REL_VENDAS DENTRO DO BANCO EMPRESA COMO*/
SELECT/*SELECIONE*/
	CEILING(SUM(VALOR_DA_VENDA))/*ARREDONDAMENTO DA SOMA DA COLUNA VALOR_DA_VENDA*/
	AS "VALOR TOTAL VENDAS",/*COMO*/
	CEILING(AVG(VALOR_DA_VENDA))/*ARREDONDAMENTO DA MEDIA DA COLUNA VALOR_DA_VENDA*/
	AS "MEDIA VALOR TOTAL VENDAS",/*COMO*/
	MAX(VALOR_DA_VENDA)/*VALOR MAXIMO DA COLUNA VALOR_DA_VENDA*/
	AS "PRECO MAXIMO VENDAS",/*COMO*/
	MIN(VALOR_DA_VENDA)/*VALOR MINIMO DA COLUNA VALOR_DA_VENDA*/
	AS "PRECO MINIMO VENDAS",/*COMO*/
	MAX(VALOR_DA_VENDA) - MIN(VALOR_DA_VENDA)/*VALOR MAXIMO DIMINUIDO PELO VALOR MINIMO DA COLUNA VALOR_DA_VENDA*/
	AS "AMPLIT.TOTAL",/*COMO*/
	ROUND(STDDEV_POP(VALOR_DA_VENDA))/*ARREDONDAMENTO DO DESVIO PADRAO DA COLUNA VALOR_DA_VENDA*/
	AS "DESV.PADRAO",/*COMO*/
	ROUND(VAR_POP(VALOR_DA_VENDA),2)/*ARREDONDAMENTO DA VARIANCIA DA COLUNA VALOR_DA_VENDA TRAZENDO 2 NUMEROS DEPOIS DA VIRGULA*/
	AS "VARIANCIA",/*COMO*/
	ROUND(/*ARREDONDAMENTO DO RESULTADO*/
		(STDDEV_POP(VALOR_DA_VENDA) / AVG(VALOR_DA_VENDA)) * 100,/*DESVIO PADRAO DA COLUNA DIVIDIDO PELA MEDIA DA COLUNA MULTIPLICADO POR 100)*/
		2/*TRAZENDO 2 NUMEROS DEPOIS DA VIRGULA*/
	)
	AS "COEF.VARIACAO",/*COMO*/
	COUNT(ID_VENDAS)/*CONTAGEM DE QUANTOS DADOS TEM A COLUNA ID_VENDAS*/
	AS "QTD.VENDAS"/*COMO*/
FROM/*DENTRO DE*/
	REL_VENDAS;/*VIEW*/
```

<h3>
   ⛏️Selecionando a VIEW(VISUALIZAÇÃO) CALC_REL_VENDAS Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.CALC_REL_VENDAS;/*VIEW CALC_REL_VENDAS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+--------------------+--------------------------+---------------------+---------------------+--------------+----------+-----------+---------------+------------+
| VALOR TOTAL VENDAS | MEDIA VALOR TOTAL VENDAS | PRECO MAXIMO VENDAS | PRECO MINIMO VENDAS | AMPLIT.TOTAL | DESV.PAD | VARIANCIA | COEF.VARIACAO | QTD.VENDAS |
+--------------------+--------------------------+---------------------+---------------------+--------------+----------+-----------+---------------+------------+
| 8222               | 1175                     | 2145.00             | 94.00               | 2051.00      | 606      | 366777.5  | 51.57         | 7          |
+--------------------+--------------------------+---------------------+---------------------+--------------+----------+-----------+---------------+------------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) CALC_REL_VENDAS que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETE A SELECAO*/
	'VALOR TOTAL VENDAS',
	'MEDIA VALOR TOTAL VENDAS',
	'PRECO MAXIMO VENDAS',
	'PRECO MINIMO VENDAS',
	'AMPLIT.TOTAL',
	'DESV.PADRAO',
	'VARIANCIA',
	'COEF.VARIACAO',
	'QTD.VENDAS'
UNION ALL/*UNIAO DE TODOS*/
SELECT/*SELECIONE AS COLUNAS*/
	`VALOR TOTAL VENDAS`,
	`MEDIA VALOR TOTAL VENDAS`,
	`PRECO MAXIMO VENDAS`,
	`PRECO MINIMO VENDAS`,
	`AMPLIT.TOTAL`,
	`DESV.PADRAO`,
	`VARIANCIA`,
	`COEF.VARIACAO`,
	`QTD.VENDAS`
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.CALC_REL_VENDAS.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	LINES TERMINATED BY '\n'/*LINHAS TERMINADAS POR*/
FROM/*DENTRO DE*/
	EMPRESA.CALC_REL_VENDAS;/*VIEW CALC_REL_VENDAS DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\82-printdatabelacalcrelvendasexportada.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1pcBxkYb1vgfY7vvf3rtQe4ePxB9qNVvm/view?usp=share_link
</p>

<h3>
   ⛏️Criando VIEW(VISUALIZAÇÃO) RANKING_PROD_MAIS_VENDIDO que Mostra o Ranking dos Produtos Mais Vendidos
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW RANKING_PROD_MAIS_VENDIDO/*CRIE A VIEW RANKING_PROD_MAIS_VENDIDO*/
AS/*COMO*/
SELECT/*SELECIONE*/
	COUNT(V.ID_VENDA_PROD)/*CONTAGEM DA COLUNA ID_VENDA_PROD DENTRO DA TABELA VENDAS*/
	AS "QUANTIDADE DE VEZES QUE O PROD FOI VENDIDO",/*COMO*/
	P.NOME_PROD/*COLUNA NOME_PROD DENTRO DA TABELA PRODUTOS*/
	AS "NOME DO PRODUTO",/*COMO*/
	P.PRECO/*COLUNA PRECO DENTRO DA TABELA PRODUTOS*/
FROM/*DENTRO DE*/
	VENDAS V/*TABELA VENDAS APELIDADA COMO V*/
	INNER JOIN PRODUTOS P/*JUNCAO INTERNA COM A TABELA PRODUTOS APELIDADA P*/
	ON V.ID_VENDA_PROD = P.ID_PROD/*SOBRE A COLUNA ID_VENDA_PROD(DENTRO DA TABELA VENDAS) SENDO IGUAL A COLUNA ID_PROD(DENTRO DA TABELA PRODUTOS)*/
GROUP BY/*AGRUPE POR*/
	ID_VENDA_PROD/*COLUNA*/
ORDER BY/*ORDENE POR*/
	COUNT(V.ID_VENDA_PROD)/*CONTAGEM DA COLUNA ID_VENDA_PROD DENTRO DA TABELA VENDAS*/
	DESC;/*DE FORMA DECRESCENTE*/
```

<h3>
   ⛏️Selecionando a VIEW(VISUALIZAÇÃO) RANKING_PROD_MAIS_VENDIDO que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.RANKING_PROD_MAIS_VENDIDO;/*VIEW RANKING_PROD_MAIS_VENDIDO DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+--------------------------------------------+------------------+---------+
| QUANTIDADE DE VEZES QUE O PROD FOI VENDIDO | NOME DO PRODUTO  | PRECO   |
+--------------------------------------------+------------------+---------+
| 2                                          | POCCO X3 PRO     | 1400.00 |  
| 1                                          | POCCO X4 PRO     | 1600.00 |  
| 1                                          | S22 ULTRA        | 602.99  |  
| 1                                          | UP PLAY          | 94.00   |  
| 1                                          | HUAWEI X8 SILVER | 1330.12 |  
| 1                                          | LG K61           | 1249.00 |  
+---------------------------------------------------------------+---------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) RANKING_PROD_MAIS_VENDIDO que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETANDO SELECAO*/
	'QUANTIDADE DE VEZES QUE O PROD FOI VENDIDO',
	'NOME DO PRODUTO',
	'PRECO'
UNION ALL/*UNIAO DE TODOS*/
SELECT/*SELECIONE*/
	COUNT(V.ID_VENDA_PROD),/*CONTAGEM DA COLUNA ID_VENDA_PROD DENTRO DA TABELA VENDAS*/
	P.NOME_PROD,/*COLUNA NOME_PROD DENTRO DA TABELA PRODUTOS*/
	P.PRECO/*COLUNA PRECO DENTRO DA TABELA PRODUTOS*/
FROM/*DENTRO DE*/
	(
		SELECT/*SELECIONE*/
			ID_VENDA_PROD,/*COLUNA*/
			COUNT(ID_VENDA_PROD) AS QTD_VENDAS/*CONTAGEM DA COLUNA ID_VENDA_PROD COMO QTD_VENDAS*/
		FROM/*DENTRO DE*/
			VENDAS/*TABELA*/
		GROUP BY/*AGRUPADO POR*/
			ID_VENDA_PROD/*COLUNA*/
		ORDER BY/*ORDENE POR*/
			QTD_VENDAS DESC/*COLUNA DE FORMA DECRESCENTE*/
	) V/*TABELA TEMPORARIA NOMEADA COMO V*/
	INNER JOIN PRODUTOS P/*JUNCAO INTERNA DA TABELA PRODUTOS APELIDADA P*/
	ON V.ID_VENDA_PROD = P.ID_PROD/*SOBRE O VALOR DA COLUNA ID_VENDA_PROD(DENTRO DA TABELA VENDAS) SENDO IGUAL AO VALOR DA COLUNA ID_PROD(DENTRO DA TABELA PRODUTOS)*/
GROUP BY/*AGRUPE POR*/
	P.NOME_PROD,/*COLUNA NOME_PROD DENTRO DA TABELA PRODUTOS*/
	P.PRECO/*COLUNA P.PRECO DENTRO DA TABELA PRODUTOS*/
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.RANKING_PROD_MAIS_VENDIDO.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	LINES TERMINATED BY '\n';/*LINHAS TERMINADAS POR*/
```

<h3>
   ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\88-printdatabelaempresarankingprodmaisvendido.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1XR-KxxTvzk5z6LISRaQWu7JA1yPzqWZ9/view?usp=share_link
</p>

<h3>
    ⛏️Criando VIEW(VISUALIZAÇÃO) RANKING_CLIENTES_QUE_MAIS_COMPRARAM que Mostra o Ranking dos Clientes que Mais Compraram
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW RANKING_CLIENTES_QUE_MAIS_COMPRARAM/*CRIE A VIEW RANKING_CLIENTES_QUE_MAIS_COMPRARAM*/
AS/*COMO*/
SELECT/*SELECIONE*/
	C.NOME_CLIENTE AS "NOME DO CLIENTE",/*COLUNA NOME_CLIENTE DENTRO DA TABELA CLIENTES COMO*/
	COUNT(*) AS "QUANTIDADE DE COMPRAS"/*CONTAGEM DE TUDO COMO*/
FROM/*DENTRO DE*/
	CLIENTES C/*TABELA CLIENTES APELIDADA COMO C*/
	JOIN VENDAS V/*JUNCAO DA TABELA VENDAS APELIDADA COMO V*/
	ON C.ID_CLIENTE = V.ID_VENDA_CLIENTE/*SOBRE A COLUNA ID_CLIENTE(DENTRO DA TABELA CLIENTES) SENDO IGUAL A COLUNA ID_VENDA_CLIENTE(DENTRO DA TABELA VENDAS)*/
GROUP BY/*AGRUPE POR*/
	C.ID_CLIENTE/*COLUNA ID_CLIENTE DENTRO DA TABELA CLIENTES*/
ORDER BY/*AGRUPE POR*/
	COUNT(*) DESC;/*CONTAGEM DE TUDO DE FORMA DECRESCENTE*/
```

<h3>
   ⛏️Selecionando a VIEW(VISUALIZAÇÃO) RANKING_CLIENTES_QUE_MAIS_COMPRARAM que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.RANKING_CLIENTES_QUE_MAIS_COMPRARAM;/*VIEW RANKING_CLIENTES_QUE_MAIS_COMPRARAM DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+-------------------------+-----------------------+
| NOME DO CLIENTE         | QUANTIDADE DE COMPRAS |
+-------------------------+-----------------------+
| PRISCILA DA COSTA NUNES | 4                     |    
| JULIA BONITA            | 1                     |              
| ROSA SANTOS SOARES      | 1                     |               
| LARISSA PAULA GUEDES    | 1                     |               
+-------------------------+-----------------------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) RANKING_CLIENTES_QUE_MAIS_COMPRARAM que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETANDO SELECAO*/
    'NOME DO CLIENTE',
    'QUANTIDADE DE COMPRAS'
UNION ALL/*UNIAO DE TODOS*/
SELECT/*SELECIONE*/
    */*TUDO*/
    INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.RANKING_CLIENTES_QUE_MAIS_COMPRARAM.csv'/*NO ARQUIVO DE SAIDA*/
    FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
    OPTIONALLY ENCLOSED BY '"'/*OPCIONALMENTE INCLUIDOS POR LINHAS*/
    LINES TERMINATED BY '\n'/*LINHAS TERMINADAS POR*/
FROM/*DENTRO DE*/
    EMPRESA.RANKING_CLIENTES_QUE_MAIS_COMPRARAM;/*VIEW RANKING_CLIENTES_QUE_MAIS_COMPRARAM DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\94-printdatabelanoexcelempresarankingclientesquemaiscompraram.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1PtlGMjykdjalKukeOphemlz_gBN5B-ZM/view?usp=share_link
</p>

<h3>
   ⛏️Criando VIEW(VISUALIZAÇÃO) QTD_CLIENTE_POR_REGIAO que Mostra a Quantidade de Clientes por Região
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW QTD_CLIENTE_POR_REGIAO/*CRIE A VIEW QTD_CLIENTE_POR_REGIAO DENTRO DO BANCO EMPRESA*/
AS/*COMO*/
SELECT/*SELECIONE*/
	PAIS,/*COLUNA*/
	UF,/*COLUNA*/
	COUNT(*) AS "QUANTIDADE DE CLIENTES"/*CONTAGEM DE TUDO COMO*/
FROM/*DENTRO DE*/
	CLIENTES/*TABELA*/
GROUP BY/*AGRUPE POR*/
	PAIS,/*COLUNA*/
	UF/*COLUNA*/
ORDER BY/*ORDENE POR*/
	PAIS,/*COLUNA*/
	UF;/*COLUNA*/

SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.QTD_CLIENTE_POR_REGIAO;/*TABELA QTD_CLIENTE_POR_REGIAO DENTRO DO BANCO EMPRESA*/
```

<h3>
    ⛏️Selecionando a VIEW(VISUALIZAÇÃO) QTD_CLIENTE_POR_REGIAO que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.QTD_CLIENTE_POR_REGIAO;/*TABELA QTD_CLIENTE_POR_REGIAO DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----------+----+------------------------+
| PAIS     | UF | QUANTIDADE DE CLIENTES |
+----------+----+------------------------+
| BELGICA  | BX | 1                      |    
| BRASIL   | BA | 2                      |
| BRASIL   | GO | 2                      |              
| BRASIL   | MA | 2                      |               
| BRASIL   | MG | 1                      |
| BRASIL   | MT | 1                      |
| BRASIL   | SC | 1                      |               
| HONDURAS | DF | 1                      |
+----------+----+------------------------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) QTD_CLIENTE_POR_REGIAO que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
(
	SELECT/*PROJETANDO SELECAO*/
		'PAIS',
		'UF',
		'QUANTIDADE DE CLIENTES'
)
UNION ALL/*UNIAO DE TODOS*/
(
	SELECT/*SELECIONE*/
		PAIS,/*COLUNA*/
		UF,/*COLUNA*/
		COUNT(*)/*CONTAGEM DE TUDO*/
	FROM/*DENTRO DE*/
		CLIENTES/*TABELA*/
	GROUP BY/*AGRUPE POR*/
		PAIS,/*COLUNA*/
		UF/*COLUNA*/
	ORDER BY/*ORDENE POR*/
		PAIS,/*COLUNA*/
		UF/*COLUNA*/
)
INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.QTD_CLIENTE_POR_REGIAO.csv'/*NO ARQUIVO DE SAIDA*/
FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
ENCLOSED BY '"'/*CERCADO POR*/
LINES TERMINATED BY '\n';/*LINHAS TERMINADAS POR*/
```

<h3>
    ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\100-printdatabelanoexcelempresaqtdclienteporregiao.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1YIy1RBXBJ9ZpaWx4cmeAYR-ILaAYFkl9/view?usp=share_link
</p>

<h3>
    ⛏️Criando VIEW(VISUALIZAÇÃO) PROD_PRECO_ABAIXO_IGUAL_MD que Mostra os Produtos que Tem o Preço Abaixo ou Igual a Média
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW PROD_PRECO_ABAIXO_IGUAL_A_MEDIA/*CRIE A VIEW PROD_PRECO_ABAIXO_IGUAL_A_MEDIA*/
AS/*COMO*/
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	PRODUTOS/*TABELA*/
WHERE/*ONDE*/
	PRECO <=/*O PRECO SEJA MENOR OU IGUAL A*/
	(
		SELECT/*SELECIONE*/
			AVG(PRECO)/*MEDIA DA COLUNA PRECO*/
		FROM/*DENTRO*/
			PRODUTOS/*DA TABELA PRODUTOS*/
	)
ORDER BY/*ORDENE POR*/
	ID_PROD ASC;/*COLUNA ID_PROD DE FORMA CRESCENTE*/
```

<h3>
    ⛏️Selecionando a VIEW(VISUALIZAÇÃO) PROD_PRECO_ABAIXO_IGUAL_A_MEDIA; que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.PROD_PRECO_ABAIXO_IGUAL_A_MEDIA;/*VIEW PROD_PRECO_ABAIXO_IGUAL_A_MEDIA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+----------+-------------------+------------------+---------+--------------+
| ID_PROD  | NOME_PROD         | MARCA            | PRECO   | COD_BARRAS   |
+----------+-------------------+------------------+---------+--------------+
| 1        | POCCO X3 PRO      | XIAOMI           | 1400.00 | 784512454547 |
| 2        | POCCO X4 PRO      | XIAOMI EVOLUTION | 1600.00 | 123123456784 |
| 4        | S22 ULTRA         | POSITIVO         | 602.99  | 47895656523  |
| 6        | C20               | NOKIA            | 573.00  | 789444475454 |
| 7        | UP PLAY           | MULTILASER       | 94.00   | 741526487777 |
| 8        | HUAWEI X8 SILVER  | HONOR            | 1330.12 | 321452145784 |
| 9        | LG K61            | LG               | 1249.00 | 741256353535 |
| 10       | LG K52            | LG               | 989.90  | 745896565652 |
+----------+-------------------+------------------+---------+--------------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) PROD_PRECO_ABAIXO_IGUAL_A_MEDIA que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
SELECT/*PROJETE A SELECAO*/
	'ID_PROD',
	'NOME_PROD',
	'MARCA',
	'PRECO',
	'COD_BARRAS'
UNION ALL/*UNIAO DE TUDO*/
SELECT/*SELECIONE AS COLUNAS*/
	ID_PROD,
	NOME_PROD,
	MARCA,
	PRECO,
	COD_BARRAS
FROM/*DENTRO DE*/
	EMPRESA.PROD_PRECO_ABAIXO_IGUAL_A_MEDIA/*VIEW PROD_PRECO_ABAIXO_IGUAL_A_MEDIA QUE ESTA DENTRO DO BANCO EMPRESA*/
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.PRODUTOS_PRECO_ABAIXO_IGUAL_MEDIA.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	ENCLOSED BY '"'/*CERCADO POR*/
	LINES TERMINATED BY '\n';/*LINHAS TERMINADAS POR*/
```

<h3>
    ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\106-printdatabeladentrodoexcelempresaprodutosprecoabaixoigualamedia.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1WdOABSafP0zuWqpTYa1GuD3c7hweAlqB/view?usp=share_link
</p>

<h3>
   ⛏️Criando VIEW(VISUALIZAÇÃO) PROD_PRECO_ACIMA_MD que Mostra os Produtos que Tem o Acima da Média
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/		
CREATE VIEW PROD_PRECO_ACIMA_DA_MEDIA/*CRIE A VIEW PROD_PRECO_ACIMA_DA_MEDIA DENTRO DO BANCO EMPRESA*/
AS/*COMO*/
SELECT/*SELECIONE*/	
	*/*TUDO*/
FROM/*DENTRO DE*/
	PRODUTOS/*DA TABELA PRODUTOS*/
WHERE/*ONDE*/
	PRECO >/*O PRECO E MAIOR QUE*/
	(
		SELECT/*SELECIONE*/
			AVG(PRECO)/*MEDIA DA COLUNA PRECO*/
		FROM/*DENTRO DE*/
			PRODUTOS/*TABELA*/
	)
ORDER BY/*ORDENE POR*/
	ID_PROD ASC;/*COLUNA DE FORMA CRESCENTE*/
```

<h3>
   ⛏️Selecionando a VIEW(VISUALIZAÇÃO) PROD_PRECO_ACIMA_DA_MEDIA que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.PROD_PRECO_ACIMA_DA_MEDIA;/*VIEW PROD_PRECO_ACIMA_DA_MEDIA QUE ESTA DENTRO DO BANCO EMPRESA*/
```

<h3>
    ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+---------+-------------------+------------------+---------+--------------+
| ID_PROD | NOME_PROD         | MARCA            | PRECO   | COD_BARRAS   |
+---------+-------------------+------------------+---------+--------------+
| 5       | IPHONE 13 PRO MAX | APPLE            | 8999.90 | 315896547831 |
+---------+-------------------+------------------+---------+--------------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) EMPRESA.PROD_PRECO_ACIMA_DA_MEDIA que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETE A SELECAO*/
	'ID_PROD',
	'NOME_PROD',
	'MARCA',
	'PRECO',
	'COD_BARRAS'
UNION ALL/*UNIAO DE TODOS*/
SELECT/*SELECIONE AS COLUNAS*/
	ID_PROD,
	NOME_PROD,
	MARCA,
	PRECO,
	COD_BARRAS
FROM/*DENTRO DE*/
	EMPRESA.PROD_PRECO_ACIMA_DA_MEDIA/*VIEW PROD_PRECO_ACIMA_DA_MEDIA QUE ESTA DENTRO DO BANCO EMPRESA*/
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.PROD_PRECO_ACIMA_MEDIA.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	ENCLOSED BY '"'/*CERCADO POR*/
	LINES TERMINATED BY '\n';/*LINHAS TERMINADAS POR*/
```

<h3>
   ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\112-printdatabelaexportadaempresaprodprecoacimamedia.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1-J55bnvSSpE41LrcpXlfevrqA-t9NJj5/view?usp=share_link
</p>

<h3>
   ⛏️Criando VIEW(VISUALIZAÇÃO) CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD que Mostra os Clientes que Compraram Produtos com Preço Abaixo ou Igual da Média
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD/*CRIE A VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD*/ 
AS/*COMO*/
SELECT/*SELECIONE*/
	V.ID_VENDAS,/*COLUNA ID_VENDAS DENTRO DA TABELA VENDAS*/
	C.NOME_CLIENTE AS "CLIENTE",/*COLUNA NOME_CLIENTE DENTRO DA TABELA CLIENTES COMO "CLIENTE"*/
	P.NOME_PROD AS "PRODUTO",/*COLUNA NOME_PROD DENTRO DA TABELA PRODUTOS COMO "PRODUTO"*/
	P.PRECO/*COLUNA PRECO DENTRO DA TABELA PRODUTOS*/
FROM/*DENTRO DE*/
	CLIENTES C/*TABELA CLIENTES APELIDADA COMO C*/
	JOIN VENDAS V/*JUNCAO COM A TABELA VENDAS APELIDADA COMO V*/
	ON V.ID_VENDA_CLIENTE = C.ID_CLIENTE/*SOBRE A COLUNA ID_VENDA_CLIENTE DENTRO DA TABELA VENDAS SENDO IGUAL A COLUNA ID_CLIENTE DENTRO DA TABELA CLIENTES*/
	JOIN PRODUTOS P/*JUNCAO COM A TABELA PRODUTOS APELIDADA COMO P*/
	ON P.ID_PROD = V.ID_VENDA_PROD/*SOBRE A COLUNA ID_PROD DENTRO DA TABELA PRODUTOS SENDO IGUAL A COLUNA ID_VENDA_PROD DENTRO DA TABELA VENDAS*/
WHERE/*ONDE*/
	P.PRECO <=/*COLUNA PRECO DENTRO DA TABELA PRODUTOS E MENOR OU IGUAL*/
	(
		SELECT/*SELECAO*/
			AVG(PRECO)/*MEDIA DA COLUNA PRECO*/
		FROM/*DENTRO DE*/
			PRODUTOS/*TABELA PRODUTOS*/
	)
ORDER BY/*ORDENE POR*/
	C.NOME_CLIENTE ASC;/*COLUNA NOME_CLIENTE DENTRO DA TABELA CLIENTES DE FORMA CRESCENTE*/
```

<h3>
    ⛏️Selecionando a VIEW(VISUALIZAÇÃO) CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD;/*VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD QUE ESTA DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+-----------+--------------------------+------------------+---------+
| ID_VENDAS | CLIENTE                  | PRODUTO          | PRECO   |
+-----------+--------------------------+------------------+---------+
| 7         | JULIA BONITA             | POCCO X3 PRO     | 1400.00 |
| 9         | LARISSA PAULA GUEDES     | HUAWEI X8 SILVER | 1330.12 |
| 11        | PRISCILA DA COSTA NUNES  | POCCO X3 PRO     | 1400.00 |
| 12        | PRISCILA DA COSTA NUNES  | POCCO X4 PRO     | 1600.00 |
| 13        | PRISCILA DA COSTA NUNES  | S22 ULTRA        | 602.99  |
| 10        | PRISCILA DA COSTA NUNES  | LG K61           | 1249.00 |
| 8         | ROSA SANTOS SOARES       | UP PLAY          | 94.00   |
+-----------+--------------------------+------------------+---------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETANDO SELECAO*/
	'ID_VENDAS',
	'CLIENTE',
	'PRODUTO',
	'PRECO'
UNION ALL/*UNIAO DE TODOS*/
SELECT/*SELECIONE AS COLUNAS*/
	ID_VENDAS,
	CLIENTE,
	PRODUTO,
	PRECO
FROM/*DENTRO DE*/
	EMPRESA.CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD/*VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ABAIXO_IGUAL_MD QUE FICA DENTRO DO BANCO EMPRESA*/
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.CLIENTES_COMPRARAM_PRECO_ABAIXO_MEDIA.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	ENCLOSED BY '"'/*CERCADO POR*/
	LINES TERMINATED BY '\n';/*LINHAS TERMINADAS POR*/
```

<h3>
   ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\118-printdatabelaempresaclientesquecompraramprodprecoabaixoigualmd.png"> 
</p>

<h3>
	⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
	https://drive.google.com/file/d/1dKWR9fXODbyJlkQ53pBwRRjrwAG1BUqZ/view?usp=sharing
</p>

<h3>
   ⛏️Criando VIEW(VISUALIZAÇÃO) CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD que Mostra os que Mostra os Clientes que Compraram Produtos com Preço Acima da Média
</h3>

```sql
USE EMPRESA;/*USE O BANCO EMPRESA*/
CREATE VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD/*CRIE A VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD*/
AS/*COMO*/
SELECT/*SELECIONE*/
	V.ID_VENDAS,/*COLUNA ID_VENDAS DENTRO DA TABELA VENDAS*/
	C.NOME_CLIENTE AS "CLIENTE",/*COLUNA NOME_CLIENTE DENTRO DA TABELA CLIENTES COMO "CLIENTE"*/
	P.NOME_PROD AS "PRODUTO",/*COLUNA NOME_PROD DENTRO DA TABELA PRODUTOS COMO "PRODUTO"*/
	P.PRECO/*COLUNA PRECO DENTRO DA TABELA PRODUTOS*/	
FROM/*DENTRO DE*/
	CLIENTES C/*TABELA CLIENTES APELIDADA COMO C*/
	JOIN VENDAS V/*JUNCAO COM A TABELA VENDAS APELIDADA COMO V*/
	ON V.ID_VENDA_CLIENTE = C.ID_CLIENTE/*SOBRE A COLUNA ID_VENDA_CLIENTE(DENTRO DA TABELA VENDAS) SENDO IGUAL A COLUNA ID_CLIENTE DENTRO DA TABELA CLIENTES*/
	JOIN PRODUTOS P/*JUNCAO COM A TABELA PRODUTOS APELIDADA COMO P*/
	ON P.ID_PROD = V.ID_VENDA_PROD/*SOBRE A COLUNA ID_PROD(DENTRO DA TABELA PRODUTOS) SENDO IGUAL A COLUNA ID_VENDA_PROD DENTRO DA TABELA VENDAS*/
WHERE/*ONDE*/
	P.PRECO >/*COLUNA PRECO DENTRO DA TABELA PRODUTOS SEJA MAIOR*/ 
	(
		SELECT/*SELECIONE*/
			AVG(PRECO)/*MEDIA DA COLUNA PRECO*/
		FROM/*DENTRO DE*/
			PRODUTOS/*TABELA*/
	)
ORDER BY/*ORDENE POR*/
	C.NOME_CLIENTE ASC;/*VIEW NOME_CLIENTE DENTRO DA TABELA CLIENTES DE FORMA CRESCENTE*/
```

<h3>
    ⛏️Selecionando a VIEW(VISUALIZAÇÃO) CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD que Está Dentro do Banco EMPRESA
</h3>

```sql
SELECT/*SELECIONE*/
	*/*TUDO*/
FROM/*DENTRO DE*/
	EMPRESA.CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD;/*VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD DENTRO DO BANCO EMPRESA*/
```

<h3>
   ⛏️Resultado do SELECT(SELEÇÃO)
</h3>

```sql
+-----------+--------------------------+-----------------+---------+
| ID_VENDAS | CLIENTE                  | PRODUTO         | PRECO   |
+-----------+--------------------------+-----------------+---------+
|           |                          |                 |         |
+-----------+--------------------------+-----------------+---------+
```

<h3>
    ⛏️Exportando a VIEW(VISUALIZAÇÃO) CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD que Está Dentro do Banco EMPRESA Para Dentro de uma Pasta, o Arquivo Fica no Formato .csv, Dentro do Script Foi Passado Alguns Parâmetros Para que ao Abrir o Arquivo Dentro de Uma Planilha o Mesmo Já Venha Formatado
</h3>

```sql
SELECT/*PROJETANDO SELECAO*/
	'ID_VENDAS',
	'CLIENTE',
	'PRODUTO',
	'PRECO'
UNION ALL/*UNIAO DE TODOS*/
SELECT/*SELECIONE AS COLUNAS*/
	ID_VENDAS,
	CLIENTE,
	PRODUTO,
	PRECO
FROM/*DENTRO DE*/
	EMPRESA.CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD/*DENTRO DA VIEW CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD QUE FICA DENTRO DO BANCO EMPRESA*/
	INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/EMPRESA.CLIENTES_QUE_COMPRARAM_PROD_PRECO_ACIMA_MD.csv'/*NO ARQUIVO DE SAIDA*/
	FIELDS TERMINATED BY ';'/*CAMPOS TERMINADOS POR*/
	ENCLOSED BY '"'/*CERCADO POR*/
	LINES TERMINATED BY '\n';/*LINHAS TERMINADAS POR*/
```

<h3>
    ⛏️Segue o Print do Arquivo .csv Aberto Dentro do Excel
</h3>

<p>
    <img src = "imagensedocumentos\124-printdatabelaempresaclientesquecompraramprodprecoacimamd.png"> 
</p>

<h3>
   ⛏️Segue o Link para Download do Arquivo .csv que Foi Gerado
</h3>

<p>
    https://drive.google.com/file/d/1dKWR9fXODbyJlkQ53pBwRRjrwAG1BUqZ/view?usp=share_link
</p>

<h3>
   📌Ferramentas Utilizadas 
</h3>

<li>
    MySQL Workbench 8.0 CE
</li>

<p>
Ferramenta gráfica de administração e desenvolvimento de banco de dados MySQL, um SGBD(Sistema de Gerenciamento de Banco de Dados) usado para executar os códigos retornando o resultado dos mesmos permitindo uma visualização mais didática do banco de dados.
</p>

<li>
    MySQL Workbench 8.0 Command Line Client
</li>

<p>
Espécie de terminal usada para executar os códigos e obter resultados dos mesmos diretamente no servidor MySQL.
</p>

<li>
    Notepad++ 8.4.8
</li>

<p>
Editor de texto usado para criar e indentar os códigos.

<li>
    Sublime Text 3
</li>

<p>
Editor de texto usado para criar e indentar os códigos.
</p>

<li>
    Microsoft Visual Studio Code(User) 1.78.1
</li>

<p>
Editor de texto usado para criar o arquivo README que possui extensão MARKDOWN.
</p>

<li>
    Excel Online (Office 365)
</li>

<p>
Ferramenta de planilha eletrônica usado para abrir os arquivos com extensão .csv, trazendo os mesmos dentro de uma planilha, possibilitando a edição e visualização didática do arquivo exportado.
</p>
