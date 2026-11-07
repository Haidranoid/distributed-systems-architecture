defmodule WorkhorseServiceWeb.Application do
  # See https://elixir.hexdocs.pm/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      WorkhorseServiceWeb.Telemetry,
      # Start a worker by calling: WorkhorseServiceWeb.Worker.start_link(arg)
      # {WorkhorseServiceWeb.Worker, arg},
      # Start to serve requests, typically the last entry
      WorkhorseServiceWeb.Endpoint
    ]

    # See https://elixir.hexdocs.pm/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: WorkhorseServiceWeb.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  @impl true
  def config_change(changed, _new, removed) do
    WorkhorseServiceWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
