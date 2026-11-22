defmodule WorkhorseServiceWorkersTest do
  use ExUnit.Case
  doctest WorkhorseServiceWorkers

  test "greets the world" do
    assert WorkhorseServiceWorkers.hello() == :world
  end
end
