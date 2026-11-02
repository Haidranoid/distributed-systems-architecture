defmodule WorkhorseServiceWeb.TestController do
  use WorkhorseServiceWeb, :controller

  def index(conn, _params) do
    res = %{foo: "foo", bar: "bar"}
    json(conn, res)
  end
end
