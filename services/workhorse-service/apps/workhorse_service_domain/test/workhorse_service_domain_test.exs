defmodule WorkhorseServiceDomainTest do
  use ExUnit.Case
  doctest WorkhorseServiceDomain

  test "greets the world" do
    assert WorkhorseServiceDomain.hello() == :world
  end
end
