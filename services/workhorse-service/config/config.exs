# This file is responsible for configuring your umbrella
# and **all applications** and their dependencies with the
# help of the Config module.
#
# Note that all applications in your umbrella share the
# same configuration and dependencies, which is why they
# all use the same configuration file. If you want different
# configurations or dependencies per app, it is best to
# move said applications out of the umbrella.
import Config

# Configure Mix tasks and generators
config :workhorse_service,
  ecto_repos: [WorkhorseService.Repo]

# Configure the mailer
#
# By default it uses the "Local" adapter which stores the emails
# locally. You can see the emails in your browser, at "/dev/mailbox".
#
# For production it's recommended to configure a different adapter
# at the `config/runtime.exs`.
config :workhorse_service, WorkhorseService.Mailer, adapter: Swoosh.Adapters.Local

config :workhorse_service_web,
  ecto_repos: [WorkhorseService.Repo],
  generators: [context_app: :workhorse_service]

# Configures the endpoint
config :workhorse_service_web, WorkhorseServiceWeb.Endpoint,
  url: [host: "localhost"],
  adapter: Bandit.PhoenixAdapter,
  render_errors: [
    formats: [json: WorkhorseServiceWeb.ErrorJSON],
    layout: false
  ],
  pubsub_server: WorkhorseService.PubSub,
  live_view: [signing_salt: "D6zr9Txs"]

# Configure Elixir's Logger
config :logger, :default_formatter,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{config_env()}.exs"
