defmodule WorkhorseService.Application do
  # See https://elixir.hexdocs.pm/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  @impl true
  def start(_type, _args) do
    children = [
      WorkhorseService.Repo,
      {DNSCluster, query: Application.get_env(:workhorse_service, :dns_cluster_query) || :ignore},
      {Phoenix.PubSub, name: WorkhorseService.PubSub}
      # Start a worker by calling: WorkhorseService.Worker.start_link(arg)
      # {WorkhorseService.Worker, arg}
    ]

    Supervisor.start_link(children, strategy: :one_for_one, name: WorkhorseService.Supervisor)
  end
end
