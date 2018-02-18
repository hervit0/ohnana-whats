defmodule SessionTest do
  use ExUnit.Case
  @unexisting_session_id "fcfe5f21-8a08-4c9a-9f97-29d2fd6a27b9"

  describe "POST /session - correct authorization" do
    test "returns 201 Created response" do
      headers = Session.set_authorized_headers()
      {:ok, %{status_code: status_code, body: body}} = Session.post_response(headers)
      data = Poison.decode!(body)["data"]

      assert status_code == 201
      assert data["type"] == "Session"
    end
  end

  describe "POST /session - incorrect authorization" do
    test "returns 401 Unauthorized response" do
      headers = Session.set_unauthorized_headers()
      {:ok, %{status_code: status_code, body: body}} = Session.post_response(headers)

      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 401
      assert errors["status"] == "401"
      assert errors["title"] == "Failed to pass authorization level"
    end
  end

  describe "GET /session - correct authorization and session exists" do
    test "returns 200 OK response" do
      session_id = Session.get_session_id()
      headers = Session.set_authorized_headers(session_id)

      {:ok, %{status_code: status_code, body: body}} = Session.get_response(headers)
      data = Poison.decode!(body)["data"]

      assert status_code == 200
      assert data["type"] == "Session"
      assert data["id"] == session_id
    end
  end

  describe "GET /session - incorrect authorization" do
    test "returns 401 Unauthorized response" do
      headers = Session.set_unauthorized_headers(@unexisting_session_id)

      {:ok, %{status_code: status_code, body: body}} = Session.get_response(headers)
      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 401
      assert errors["status"] == "401"
      assert errors["title"] == "Failed to pass authorization level"
    end
  end

  describe "GET /session - correct authorization and session does not exist" do
    test "returns 404 Not Found response" do
      headers = Session.set_authorized_headers(@unexisting_session_id)

      {:ok, %{status_code: status_code, body: body}} = Session.get_response(headers)
      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 404
      assert errors["status"] == "404"
      assert errors["title"] == "Session not found"
    end
  end
end
