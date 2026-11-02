defmodule WorkhorseServiceWeb.Router do
  use WorkhorseServiceWeb, :router

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/", WorkhorseServiceWeb do
    # pipe_through :api

    get "/hello-world", TestController, :index
  end

  scope "/api", WorkhorseServiceWeb do
    pipe_through :api
  end

  # Enable Swoosh mailbox preview in development
  if Application.compile_env(:workhorse_service, :dev_routes) do
    scope "/dev" do
      pipe_through [:fetch_session, :protect_from_forgery]

      forward "/mailbox", Plug.Swoosh.MailboxPreview
    end
  end
end
