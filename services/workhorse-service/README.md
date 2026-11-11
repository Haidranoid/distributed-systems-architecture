# Workhorse Service

Workhorse is an Elixir/Phoenix Umbrella project designed to manage jobs, tasks, scheduling, and concurrent background execution.

The project follows a modular architecture using independent OTP applications within a shared BEAM runtime.

## Getting Started

### Prerequisites

- Elixir
- Erlang/OTP
- Hex package manager
- Rebar3

### Setup

Clone the repository and navigate to its root directory:

```bash
cd workhorse-service
```

Install and setup dependencies:

```bash
mix setup
```

Start the Phoenix server:

```bash
mix phx.server
```

Alternatively, start the server inside IEx:

```bash
iex -S mix phx.server
```

The Phoenix endpoint will be available at [http://localhost:4000](http://localhost:4000).

## Project Structure

Workhorse uses an Elixir Umbrella architecture. Each application is an independent OTP application, with its own source code, dependencies, tests, and responsibilities.

```text
workhorse-service/
├── apps/
│   ├── workhorse_service/
│   ├── workhorse_service_web/
│   ├── workhorse_core/
│   ├── workhorse_jobs/
│   ├── workhorse_tasks/
│   ├── workhorse_workers/
│   └── workhorse_schedulers/
│
├── config/
├── mix.exs
├── mix.lock
└── README.md
```

### Applications

| Application | Responsibility |
|---|---|
| `workhorse_service` | Main application and Umbrella supervision entry point. |
| `workhorse_service_web` | Phoenix HTTP interface, REST API, controllers, and routing. |
| `workhorse_core` | Shared domain logic, business rules, structures, and contracts. |
| `workhorse_jobs` | Job management and lifecycle operations. |
| `workhorse_tasks` | Task definitions, composition, and execution logic. |
| `workhorse_workers` | Background execution, concurrent processes, and worker supervision. |
| `workhorse_schedulers` | Scheduling, timers, and periodic job execution. |

All applications run within the same BEAM runtime. They are separate OTP applications, not independently deployed microservices.

## Creating a New Umbrella Application

New applications should be created from the Umbrella project's root directory, inside `apps/`.

Use `mix new` followed by the desired application path:

```bash
mix new apps/workhorse_example
```

This creates a new Mix application and its corresponding source and test directories.

### Applications with Supervision Trees

Use the `--sup` flag when an application needs its own supervision tree.

```bash
mix new apps/workhorse_example --sup
```

This generates an `Application` callback module and a root Supervisor configured to start the application's child processes.

For example:

```elixir
defmodule WorkhorseExample.Application do
  use Application

  @impl true
  def start(_type, _args) do
    children = [
      # Add supervised processes here
    ]

    opts = [
      strategy: :one_for_one,
      name: WorkhorseExample.Supervisor
    ]

    Supervisor.start_link(children, opts)
  end
end
```

### When to Use `--sup`

Use `--sup` when an application owns long-running processes or infrastructure that requires supervision.

Typical examples include:

- Workers and background processes.
- GenServers.
- Registries.
- Task Supervisors.
- Schedulers and periodic processes.
- Application-specific process coordination.

### When Not to Use `--sup`

Omit `--sup` when an application primarily contains modules, functions, domain logic, data structures, or business rules without requiring its own supervision tree.

Examples include:

- Domain models and structs.
- Business rules and validations.
- Shared contracts and behaviours.
- Stateless operations and utility modules.

**Important:** An application created without `--sup` is still a valid OTP application. It can be started as part of the Umbrella and can use processes supervised by another application.

The `--sup` flag is optional and should reflect the application's runtime responsibilities, not simply its existence.

## Managing Dependencies and Compilation

Umbrella applications have their own `mix.exs` files, but dependencies are resolved and managed at the Umbrella level.

After adding a new application:

```bash
# Resolve dependencies
mix deps.get

# Compile the entire Umbrella
mix compile
```

Mix automatically discovers applications located inside the `apps/` directory.

If an application requires another Umbrella application, declare that dependency in its `mix.exs`:

```elixir
defp deps do
  [
    {:workhorse_core, in_umbrella: true}
  ]
end
```

This establishes an explicit dependency between the applications and ensures the correct compilation order.

## Useful Commands

Run the following commands from the Umbrella root directory.

### Development

| Command | Description |
|---|---|
| `mix setup` | Install and configure project dependencies. |
| `mix deps.get` | Retrieve dependencies. |
| `mix deps` | Display dependency information. |
| `mix compile` | Compile all Umbrella applications. |
| `mix clean` | Remove compiled build artifacts. |
| `mix phx.server` | Start the Phoenix server. |
| `iex -S mix phx.server` | Start Phoenix inside IEx. |

### Testing

```bash
# Run all tests
mix test

# Run tests for a specific application
mix test apps/workhorse_jobs

# Run a specific test file
mix test apps/workhorse_jobs/test/workhorse_jobs_test.exs
```

### Application Management

```bash
# Display the application dependency tree
mix app.tree

# Execute a task in the context of a specific application
mix cmd --app workhorse_jobs mix test
```

### Maintenance

```bash
# Update dependencies within their allowed version constraints
mix deps.update --all

# Check outdated dependencies (requires the Hex outdated task)
mix hex.outdated
```

## Architecture Guidelines

The following principles guide the development of Workhorse:

- **Separation of Responsibilities:** Each OTP application should have a clearly defined responsibility.
- **Explicit Dependencies:** Applications should depend on one another through declared dependencies and public interfaces.
- **Domain Independence:** Core domain logic should not depend on Phoenix, HTTP infrastructure, or external messaging systems.
- **OTP Supervision:** Long-running processes should be supervised and have clearly defined restart strategies.
- **Controlled Coupling:** Avoid unnecessary dependencies and circular references between applications.
- **Polyglot Integration:** External communication with other DSA microservices should use well-defined interfaces and messaging contracts.

## Learn More

- [Elixir Official Website](https://elixir-lang.org/)
- [Elixir Documentation](https://hexdocs.pm/elixir/)
- [Mix and OTP Guide](https://hexdocs.pm/elixir/introduction-to-mix.html)
- [Phoenix Framework](https://www.phoenixframework.org/)
- [Phoenix Guides](https://phoenix.hexdocs.pm/overview.html)
- [Phoenix Documentation](https://phoenix.hexdocs.pm/)
- [Elixir Forum](https://elixirforum.com/)
- [Phoenix Source Code](https://github.com/phoenixframework/phoenix)
