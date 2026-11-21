defmodule WorkhorseServiceWeb.Plugs.LoadScope do
  import Plug.Conn
  import Phoenix.Controller

  def init(opts), do: opts

  def call(conn, _opts) do
    conn
  end
end
