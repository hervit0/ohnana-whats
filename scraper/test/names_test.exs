defmodule NamesTest do
  use ExUnit.Case

  alias Scraper.HttpCaller
  alias Scraper.Names

  import Mock

  @category "21st-century-TV-shows-weird-label"
  @category_label "Awesome TV shows"

  describe "get_by/1" do
    test "returns names for a given catergory" do
      body = %{
        "query" => %{
          "categorymembers" => [
            %{
              "title" => "Stranger Things (Season 1)"
            },
            %{
              "title" => "Altered Carbon"
            }
          ]
        }
      }

      http_mock = [get: fn _category -> {:ok, body} end]

      with_mock HttpCaller, http_mock do
        names = Names.get_by({@category_label, @category})

        assert called(HttpCaller.get(@category))

        assert names == [
                 %{"category" => @category_label, "name" => "Stranger Things"},
                 %{"category" => @category_label, "name" => "Altered Carbon"}
               ]
      end
    end
  end
end
