# Projeto de Teste - Plugin Minecraft

Este projeto tem como objetivo facilitar o desenvolvimento e os testes locais de um plugin para Minecraft utilizando Paper.

## Funcionalidades implementadas

### `/ping`

Comando simples para validar se o plugin está funcionando corretamente.

**Exemplo:**

```text
/ping
```

**Resposta:**

```text
Pong!
```

---

### `/play <par|impar> <numero>`

Jogo de Par ou Ímpar.

O jogador escolhe:

- `par` ou `impar`
- um número entre `1` e `10`

O sistema sorteia um número aleatório entre `1` e `10`, soma com o valor informado e verifica se o resultado é par ou ímpar.

Após a partida são exibidos:

- Sua escolha
- Seu número
- Número sorteado pelo sistema
- Total da soma
- Resultado da partida
- Estatísticas atualizadas

**Exemplos:**

```text
/play par 5
```

```text
/play impar 8
```

Além disso:

- Vitória → efeitos visuais e sonoros
- Derrota → efeito sonoro de falha

---

### `/stats`

Exibe as estatísticas do jogador.

**Exemplo:**

```text
/stats
```

**Saída:**

```text
══════════════════
Partidas: 15
Vitórias: 10
Derrotas: 5
Taxa de vitória: 66.67%
══════════════════
```

As estatísticas são persistidas em banco SQLite e permanecem após reinicializações do servidor.

---

## Pré-requisitos

- Java instalado
- Bash (Linux, macOS ou Git Bash no Windows)
- Servidor Minecraft configurado

## Configuração

Copie o arquivo de exemplo:

```bash
cp example.env .env
```

Edite o arquivo `.env` e ajuste as variáveis de acordo com o seu ambiente.

## Iniciar o servidor

Para compilar o plugin e iniciar o servidor Minecraft:

```bash
./dev.sh start
```

## Atualizar o plugin sem reiniciar o servidor

Com o servidor já em execução, abra outro terminal e execute:

```bash
./dev.sh
```

O script irá recompilar o plugin e copiar o novo `.jar` para a pasta de plugins.

Em seguida, no terminal do servidor Minecraft, execute:

```text
reload confirm
```

Isso fará o recarregamento dos plugins sem a necessidade de reiniciar o servidor.

## Fluxo recomendado de desenvolvimento

1. Inicie o servidor:

```bash
./dev.sh start
```

2. Realize alterações no código do plugin.

3. Recompile e copie o plugin atualizado:

```bash
./dev.sh
```

4. No console do servidor execute:

```text
/reload confirm
```

5. Teste as alterações.
