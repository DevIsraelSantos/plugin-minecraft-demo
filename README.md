# Projeto de Teste - Plugin Minecraft

Este projeto tem como objetivo facilitar o desenvolvimento e os testes locais do plugin em um servidor Minecraft.

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
