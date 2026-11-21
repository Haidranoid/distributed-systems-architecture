defmodule WorkhorseServiceWeb.Plugs.Authenticate do
  import Plug.Conn
  import Phoenix.Controller

  def init(opts), do: opts

  def call(conn, _opts) do
    if conn.assigns.current_scope.user do
      conn
    else
      conn
      |> put_flash(:error, "Authentication is required!")
      |> redirect(to: "/")
      |> halt()
    end
  end
end
